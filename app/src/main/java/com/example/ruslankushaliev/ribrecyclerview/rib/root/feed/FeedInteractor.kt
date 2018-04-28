package com.example.ruslankushaliev.ribrecyclerview.rib.root.feed

import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.enitity.ListItem
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [FeedScope].
 */
@RibInteractor
class FeedInteractor : Interactor<FeedInteractor.FeedPresenter, FeedRouter>() {

    @Inject
    lateinit var presenter: FeedPresenter

    @Inject
    lateinit var listener: Listener

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        presenter.clickedItem.subscribe(listener::clickOnItem)
    }


    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface FeedPresenter {
        val clickedItem: Observable<ListItem>
    }

    interface Listener {
        fun clickOnItem(listItem: ListItem)
    }
}
