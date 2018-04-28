package com.example.ruslankushaliev.ribrecyclerview.rib.root.article

import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.enitity.ListItem
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ArticleScope].
 */
@RibInteractor
class ArticleInteractor : Interactor<ArticleInteractor.ArticlePresenter, ArticleRouter>() {

    @Inject
    lateinit var presenter: ArticlePresenter

    @Inject
    lateinit var listItem: ListItem

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        presenter.setListItem(listItem)

    }


    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface ArticlePresenter {
        fun setListItem(listItem: ListItem)
    }
}
