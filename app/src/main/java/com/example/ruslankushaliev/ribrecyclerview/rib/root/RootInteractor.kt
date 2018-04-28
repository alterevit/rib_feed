package com.example.ruslankushaliev.ribrecyclerview.rib.root

import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.FeedInteractor
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.enitity.ListItem
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

    @Inject
    lateinit var presenter: RootPresenter

    override fun didBecomeActive(savedInstanceState: com.uber.rib.core.Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachFeed()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter

    inner class FeedInteractorListener: FeedInteractor.Listener {
        override fun clickOnItem(listItem: ListItem) {
            router.detachFeed()
            router.attachArticle(listItem)
        }
    }
}
