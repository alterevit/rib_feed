package com.example.ruslankushaliev.ribrecyclerview.view.template.delegateadapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.ruslankushaliev.ribrecyclerview.util.extension.inflate
import kotlinx.android.extensions.LayoutContainer


abstract class BaseDelegateAdapter<in T : DelegateViewType> : IDelegateAdapter {

    abstract val itemLayoutId: Int
    abstract fun BaseViewHolder.onBind(model: T)

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            BaseViewHolder(parent.inflate(itemLayoutId)) { model, v -> v.onBind(model as T) }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateViewType) {
        (holder as BaseViewHolder).apply {
            bind(item)
        }
    }

    class BaseViewHolder(override val containerView: View,
            private val onItemInflated: (DelegateViewType, BaseViewHolder) -> Unit) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(model: DelegateViewType) = onItemInflated.invoke(model, this)
    }
}
