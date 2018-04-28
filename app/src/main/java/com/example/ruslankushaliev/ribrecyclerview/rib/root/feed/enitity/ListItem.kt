package com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.enitity

import com.example.ruslankushaliev.ribrecyclerview.view.LIST_ITEM
import com.example.ruslankushaliev.ribrecyclerview.view.template.delegateadapter.DelegateViewType

data class ListItem(val text: String) : DelegateViewType {
    override val viewType: Int
        get() = LIST_ITEM
}