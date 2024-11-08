package com.kozan.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T:Any, DB : ViewDataBinding> (private val layoutResId : Int): RecyclerView.Adapter<BaseAdapter.BaseViewHolder<DB>>() {
     var itemList: MutableList<T> = mutableListOf()
    private var onItemClickListener :((T,Int)->Unit)? = null
    class BaseViewHolder<DB : ViewDataBinding>( val binding: DB) : RecyclerView.ViewHolder(binding.root)

    abstract fun bindViewHolder(holder: BaseViewHolder<DB>, item: T, position: Int)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DB>(inflater, layoutResId, parent, false)
        return BaseViewHolder(binding)
    }
    override fun onBindViewHolder(holder: BaseViewHolder<DB>, position: Int) {
        val item = itemList[position]
        bindViewHolder(holder,item, position)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item,position)
        }
    }


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

    fun addItem(position: Int, item: T) {
        itemList.add(position, item)
        notifyItemInserted(position)
    }

    fun getItem(position : Int): T {
        return itemList[position]
    }

    fun changeItem(position: Int, item: T) {
        itemList[position] = item
        notifyItemChanged(position)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val item = itemList[fromPosition]
        itemList.removeAt(fromPosition)
        itemList.add(toPosition, item)
        notifyItemMoved(fromPosition, toPosition)

    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemList.size)
    }

    fun setOnItemClickListener(listener: (T, Int) -> Unit){
        onItemClickListener = listener
    }


}
