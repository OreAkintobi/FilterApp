package com.oreakintobi.oreakintobi.ui.filterview

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.oreakintobi.oreakintobi.R
import com.oreakintobi.oreakintobi.data.NetworkChecker.isNetworkAvailable
import com.oreakintobi.oreakintobi.databinding.FragmentFilterBinding
import com.oreakintobi.oreakintobi.entities.Account
import com.oreakintobi.oreakintobi.repositories.FilterRepository
import com.oreakintobi.oreakintobi.ui.DataLoadingListener
import com.oreakintobi.oreakintobi.utils.hide
import com.oreakintobi.oreakintobi.utils.show
import com.oreakintobi.oreakintobi.utils.snackbar
import kotlinx.android.synthetic.main.fragment_filter.*

private const val MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1

class FilterFragment : Fragment(),
    DataLoadingListener {
    private val viewModelFactory =
        FilterViewModelFactory(
            FilterRepository()
        )
    private val filterViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FilterViewModel::class.java)
    }
    private lateinit var binding: FragmentFilterBinding
    private lateinit var adapter: FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_filter, container, false
            )

        checkPermissionAndStart()
        val config = PRDownloaderConfig.newBuilder().setDatabaseEnabled(true).build()
        PRDownloader.initialize(context, config)

        val manager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = manager

        binding.filterViewModel = filterViewModel
        binding.lifecycleOwner = this
        filterViewModel.dataLoadingListener = this

        filterViewModel.filters.observe(viewLifecycleOwner, Observer {
            it as ArrayList<Account>
            adapter = FilterAdapter(
                it,
                requireContext()
            ) {
                view?.snackbar("Filter is Now Clickable")
            }
            binding.recyclerView.adapter = adapter
        })

        if (!isNetworkAvailable(context!!)!!) {
            binding.rootLayout.snackbar("No internet connection")
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    private fun checkPermissionAndStart() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            promptDialogPermission()

        } else {
            filterViewModel.checkIfFileExists()
        }
    }

    private fun promptDialogPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_WRITE_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_STORAGE -> {
                if ((grantResults.isNotEmpty() && permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        filterViewModel.checkIfFileExists()
                    }
                } else {
                    promptDialogPermission()
                }
            }
        }
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess() {
        progress_bar.hide()
        root_layout.snackbar("File Download Completed.")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }

}
