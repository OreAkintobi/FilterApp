package com.oreakintobi.oreakintobi

import android.annotation.SuppressLint
import com.oreakintobi.oreakintobi.entities.Account
import com.oreakintobi.oreakintobi.entities.CarOwner
import com.oreakintobi.oreakintobi.entities.CarOwnerList
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.BIO
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.CAR_COLOR
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.CAR_MODEL
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.COUNTRY
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.EMAIL
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.FIRST_NAME
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.GENDER
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.ID
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.JOB_TITLE
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.LAST_NAME
import com.oreakintobi.oreakintobi.utils.DataUtils.CSVHeader.YEAR
import de.siegmar.fastcsv.reader.CsvReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FilterManager {


    /*Performing a IO operations will take time,
       so it's safe to take it out of  the UI thread*/
    suspend fun readFile(file: File): CarOwnerList {

        val result = CarOwnerList()
        withContext(Dispatchers.IO) {
            try {
                val csvReader = CsvReader()
                csvReader.setFieldSeparator(',')
                csvReader.setSkipEmptyRows(true)
                csvReader.setContainsHeader(true)
                csvReader.parse(BufferedReader(FileReader(file.absolutePath)))
                    .use { csvParser ->
                        while (true) {
                            val row = csvParser.nextRow() ?: break
                            result.add(
                                CarOwner(
                                    row.getField(ID).toLong(),
                                    R.drawable.car,
                                    row.getField(FIRST_NAME),
                                    row.getField(LAST_NAME),
                                    row.getField(EMAIL),
                                    row.getField(COUNTRY),
                                    row.getField(CAR_MODEL),
                                    row.getField(YEAR),
                                    row.getField(CAR_COLOR),
                                    row.getField(GENDER),
                                    row.getField(JOB_TITLE),
                                    row.getField(BIO)
                                )
                            )
                        }
                    }
            } catch (e: Exception) {
                Timber.e(e.printStackTrace().toString())
            }
        }
        return result
    }

    @SuppressLint("DefaultLocale")
    suspend fun filter(
        list: CarOwnerList,
        criteria: Account
    ): CarOwnerList {
        val result = CarOwnerList()
        withContext(Dispatchers.IO) {

            for (i in 0 until list.size) {
                if ((criteria.gender.capitalize() == list[i].gender.capitalize())
                    or (criteria.gender.isEmpty())
                ) {
                    if ((list[i].country.capitalize() in criteria.countries.map {
                            it.capitalize()
                        })
                        or criteria.countries.isEmpty()
                    ) {
                        if ((list[i].carColor.capitalize() in criteria.colors.map {
                                it.capitalize()
                            })
                            or criteria.colors.isEmpty()
                        ) {
                            result.add(
                                CarOwner(
                                    list[i].id,
                                    R.drawable.car,
                                    list[i].firstName,
                                    list[i].lastName,
                                    list[i].email,
                                    list[i].country,
                                    list[i].carModel,
                                    list[i].year,
                                    list[i].carColor,
                                    list[i].gender,
                                    list[i].jobTitle,
                                    list[i].bio
                                )
                            )
                        }
                    }
                }
            }
        }
        return result
    }
}