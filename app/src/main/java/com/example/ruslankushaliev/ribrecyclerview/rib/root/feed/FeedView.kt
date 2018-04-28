package com.example.ruslankushaliev.ribrecyclerview.rib.root.feed

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.delegate.ListItemDelegate
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.enitity.ListItem
import com.example.ruslankushaliev.ribrecyclerview.view.LIST_ITEM
import com.example.ruslankushaliev.ribrecyclerview.view.template.delegateadapter.RibbonAdapter
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.rib_feed.view.*

/**
 * Top level view for {@link FeedBuilder.FeedScope}.
 */
class FeedView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), FeedInteractor.FeedPresenter {

    override val clickedItem: Subject<ListItem> = BehaviorSubject.create()

    override fun onFinishInflate() {
        super.onFinishInflate()
        rvFeed.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        rvFeed.adapter = RibbonAdapter(ListItemDelegate(clickedItem::onNext) to LIST_ITEM).apply {
            refresh(ListItem("string 1"), ListItem("string 2"), ListItem("string 3"))
        }

    }

}
