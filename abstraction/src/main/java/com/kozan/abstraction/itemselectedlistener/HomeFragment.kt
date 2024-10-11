package com.kozan.abstraction.itemselectedlistener

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kozan.abstraction.R
import com.kozan.abstraction.databinding.FragmentHomeBinding
import com.kozan.utils.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {



    val listener : OnItemSelectedListener by lazy {
        requireActivity() as OnItemSelectedListener
    }
   /* private var listener: OnItemSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            listener.onItemSelected(it)

        }
    }

}