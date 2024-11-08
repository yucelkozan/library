package com.kozan.sample.baseadapter

import com.kozan.sample.R
import com.kozan.sample.databinding.ItemSampleBinding
import com.kozan.utils.BaseAdapter

class SampleAdapter() : BaseAdapter<String,ItemSampleBinding>(R.layout.item_sample){

    override fun bindViewHolder(
        holder: BaseViewHolder<ItemSampleBinding>,
        item: String,
        position: Int
    ) {

        holder.binding.textView.text = item
    }

}