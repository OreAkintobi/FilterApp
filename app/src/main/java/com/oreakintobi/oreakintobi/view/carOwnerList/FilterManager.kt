package com.oreakintobi.oreakintobi.view.carOwnerList

import android.annotation.SuppressLint
import com.oreakintobi.oreakintobi.R
import com.oreakintobi.oreakintobi.models.CarOwner
import com.oreakintobi.oreakintobi.models.CarOwnerList
import com.oreakintobi.oreakintobi.models.FilterElement
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.BIO
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.CAR_COLOR
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.CAR_MODEL
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.COUNTRY
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.EMAIL
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.FIRST_NAME
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.GENDER
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.ID
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.JOB_TITLE
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.LAST_NAME
import com.oreakintobi.oreakintobi.util.Utility.CSVHeader.YEAR
import de.siegmar.fastcsv.reader.CsvReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FilterManager {
    suspend fun readFile(absoluteFile: File): CarOwnerList {
        val result = CarOwnerList()
        withContext(Dispatchers.IO) {
            try {
                val csvReader = CsvReader()
                csvReader.setFieldSeparator(',')
                csvReader.setSkipEmptyRows(true)
                csvReader.setContainsHeader(true)
                csvReader.parse(BufferedReader(FileReader(absoluteFile.absolutePath)))
                    .use { csvParser ->
                        while (true) {
                            val row = csvParser.nextRow() ?: break
                            result.add(
                                CarOwner(
                                    row.getField(ID).toLong(),
                                    R.drawable.car1,
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
        criteria: FilterElement
    ): CarOwnerList {
        val result = CarOwnerList()
        withContext(Dispatchers.IO) {

            for (i in 0 until list.size) {
                if ((criteria.gender.capitalize() == list[i].gender.capitalize())
                    or (criteria.gender.isEmpty())
                ) {
                    if ((list[i].country.capitalize() in criteria.countries.map { it.capitalize() })
                        or criteria.countries.isEmpty()
                    ) {
                        if ((list[i].carColor.capitalize() in criteria.colors.map { it.capitalize() })
                            or criteria.colors.isEmpty()
                        ) {
                            result.add(
                                CarOwner(
                                    list[i].id,
                                    R.drawable.car1,
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