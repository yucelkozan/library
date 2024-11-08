package com.kozan.sample.baseadapter

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.kozan.sample.R
import com.kozan.sample.databinding.FragmentBlankBinding
import com.kozan.utils.BaseFragment
import com.kozan.utils.showToastMessage
import com.kozan.utils.updateBottomPaddingByBottomSystemBar


class HomeFragment : BaseFragment<FragmentBlankBinding>(R.layout.fragment_blank) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SampleAdapter()
        binding.recyclerView.adapter = adapter
        adapter.setItems(listOf(
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "ABEAUYLEMAU ELMAUKYLA UEMUYE AUYLEMA",
            "SON SON SON SON SON SON SON SON SON",
            ))
        adapter.setOnItemClickListener { s, i ->
        context.showToastMessage(s)
            findNavController().navigate(R.id.detailFragment,null, NavigationAnimations.slideInFromTop())
        }


        binding.recyclerView.updateBottomPaddingByBottomSystemBar()
    }

}