package com.kozan.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T:Any, DB : ViewDataBinding> (private val layoutResId : Int, private val clickedItem :(T,Int)->Unit): RecyclerView.Adapter<BaseAdapter.BaseViewHolder<DB>>() {
     var itemList: MutableList<T> = mutableListOf()
    override fun getItemCount() = itemList.size

    fun setItems(newItems: List<T>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize() = itemList.size
            override fun getNewListSize() = newItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                // Benzersiz bir özelliğe göre kıyasla (id olabilir)
                return itemList[oldItemPosition] == newItems[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                // İçerik aynı mı kontrol et (Verilerin eşitliğine göre)
                return itemList[oldItemPosition] == newItems[newItemPosition]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemList = newItems.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DB>(inflater, layoutResId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DB>, position: Int) {
        val item = itemList[position]
        bindViewHolder(holder,item, position)
        holder.itemView.setOnClickListener {
            clickedItem.invoke(item,position)
        }
    }
    abstract fun bindViewHolder(holder: BaseViewHolder<DB>, item: T, position: Int)
    class BaseViewHolder<DB : ViewDataBinding>( val binding: DB) : RecyclerView.ViewHolder(binding.root)
}