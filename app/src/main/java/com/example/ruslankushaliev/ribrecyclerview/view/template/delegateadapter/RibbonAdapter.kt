package com.example.ruslankushaliev.ribrecyclerview.view.template.delegateadapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


class RibbonAdapter constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapters = SparseArrayCompat<IDelegateAdapter>()
    private var elements: ArrayList<DelegateViewType> = ArrayList()

    override fun getItemCount() = elements.size

    constructor (vararg delegates: Delegate) : this() {
        for ((adapter, type) in delegates) putDelegate(type, adapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            adapters.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            adapters.get(getItemViewType(position))
                    .onBindViewHolder(holder, elements[position])

    override fun getItemViewType(position: Int) = elements[position].viewType

    fun putDelegate(viewType: Int, delegateAdapter: IDelegateAdapter) =
            adapters.put(viewType, delegateAdapter)

    fun add(vararg delegates: DelegateViewType) {
        elements.addAll(delegates)
        notifyItemRangeChanged(delegates.size, elements.size)
    }

    fun refresh(vararg delegates: DelegateViewType) {
        elements.clear()
        notifyDataSetChanged()
        elements.addAll(delegates)
        notifyItemRangeInserted(0, delegates.size)
    }

    fun refresh(delegates: List<DelegateViewType>) {
        elements.clear()
        notifyDataSetChanged()
        elements.addAll(delegates)
        notifyItemRangeInserted(0, delegates.size)
    }

    fun filterByViewType(viewType: Int) {
        elements.filter { it.viewType == viewType }
    }

    fun filterElements(filterFunc: List<DelegateViewType>.() -> List<DelegateViewType>) {
        elements.filterFunc()
    }

    fun filterElements(filterFunc: () -> Boolean) {
        elements.filter { filterFunc() }
    }

    fun onItemDismiss(position: Int) {
        elements.removeAt(position)
        notifyItemRemoved(position)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getItemAt(position: Int) : T = elements[position] as T

}
