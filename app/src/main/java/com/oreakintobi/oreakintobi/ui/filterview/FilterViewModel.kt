package com.oreakintobi.oreakintobi.ui.filterview

import android.os.Environment
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.oreakintobi.oreakintobi.entities.Account
import com.oreakintobi.oreakintobi.repositories.FilterRepository
import com.oreakintobi.oreakintobi.ui.DataLoadingListener
import com.oreakintobi.oreakintobi.utils.DataUtils
import com.oreakintobi.oreakintobi.utils.DataUtils.CAR_OWNER_DATA2
import timber.log.Timber
import java.io.File

@Suppress("UNCHECKED_CAST")
class FilterViewModel(
    private val repository: FilterRepository
) : ViewModel() {

    var dataLoadingListener: DataLoadingListener? = null

    private val folder by lazy {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            DataUtils.FOLDER
        )
    }

    private val file by lazy {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            DataUtils.FOLDER.plus("/$CAR_OWNER_DATA2")
        )
    }

    private var _filters = MutableLiveData<List<Account>>()
    val filters: LiveData<List<Account>>
        get() = _filters

    init {
        _filters = repository.fetchFilters() as MutableLiveData<List<Account>>
    }

    fun checkIfFileExists() {
        if (!file.exists()) {
            downloadFile()
        }
    }

    private fun downloadFile(): Int {
        if (!folder.exists()) folder.mkdir()
        return PRDownloader.download(
                DataUtils.DOWNLOAD_URL2,
                folder.absolutePath,
                CAR_OWNER_DATA2
            )
            .build()
            .setOnStartOrResumeListener {
                dataLoadingListener?.onStarted()
                Timber.i("Started")
            }
            .setOnPauseListener {
                Timber.i("Paused")
            }
            .setOnCancelListener {
                Timber.i("Cancelled")
            }
            .setOnProgressListener { }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    dataLoadingListener?.onSuccess()
                    Timber.i("Completed")
                }

                override fun onError(error: com.downloader.Error?) {
                    dataLoadingListener?.onFailure("Failed to download: ${error.toString()}")
                    Timber.e(error?.serverErrorMessage)
                    Log.e("FilterError:", error?.serverErrorMessage.toString())
                }
            })
    }
}

