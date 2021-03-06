package com.oreakintobi.oreakintobi.viewModel.filter


import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.oreakintobi.oreakintobi.models.Filter
import com.oreakintobi.oreakintobi.repository.FilterRepository
import com.oreakintobi.oreakintobi.util.Utility
import com.oreakintobi.oreakintobi.util.Utility.CAR_OWNER_DATA
import timber.log.Timber
import java.io.File


class FilterListViewModel(private val repository: FilterRepository) : ViewModel() {


    private val file by lazy {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            Utility.FOLDER
        )
    }

    private val absoluteFile by lazy {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            Utility.FOLDER.plus("/$CAR_OWNER_DATA")
        )
    }

    private var _filterList = MutableLiveData<Filter>()
    val filterList: LiveData<Filter>
        get() = _filterList


    private val _startDialogDownload = MutableLiveData<Boolean>()
    val startDialogDownload: LiveData<Boolean>
        get() = _startDialogDownload

    private val _completeDownload = MutableLiveData<Boolean>()
    val completeDownload: LiveData<Boolean>
        get() = _completeDownload


    val grantAccess = MutableLiveData<Boolean>()


    init {
        initializeFetch()
    }

    private fun initializeFetch() {
        Timber.i("Supposed called")
        repository.getFilterList()?.let {
            _filterList = it

        }

    }

    fun checkDataExist() {
        if (!absoluteFile.exists()) {
            _startDialogDownload.value = false
            startDownload()
        }


    }


    private fun startDownload(): Int {
        if (!file.exists()) file.mkdir()
        return PRDownloader.download(
                Utility.DOWNLOAD_URL,
                file.absolutePath,
                CAR_OWNER_DATA
            )
            .build()
            .setOnStartOrResumeListener {
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
                    _completeDownload.value = true
                    grantAccess.value = true
                    Timber.i("Completed")
                }

                override fun onError(error: com.downloader.Error?) {
                    Timber.e(error?.serverErrorMessage)
                    _completeDownload.value = true

                }
            })
    }


}
