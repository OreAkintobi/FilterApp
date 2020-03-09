package com.oreakintobi.oreakintobi.ui.carownerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oreakintobi.oreakintobi.R

/**
 * A simple [Fragment] subclass.
 */
class CarOwnerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_owner, container, false)
    }

}
