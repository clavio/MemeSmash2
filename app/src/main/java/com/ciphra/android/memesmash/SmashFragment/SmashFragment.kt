package com.ciphra.android.memesmash.SmashFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ciphra.android.memesmash.R

class SmashFragment : Fragment() {

    companion object {
        fun newInstance() = SmashFragment()
    }

    private lateinit var viewModel: SmashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.smash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SmashViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
