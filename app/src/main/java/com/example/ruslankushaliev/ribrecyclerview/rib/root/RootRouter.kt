package com.example.ruslankushaliev.ribrecyclerview.rib.root

import com.example.ruslankushaliev.ribrecyclerview.rib.root.article.ArticleBuilder
import com.example.ruslankushaliev.ribrecyclerview.rib.root.article.ArticleRouter
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.FeedBuilder
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.FeedRouter
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.enitity.ListItem
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 */
class RootRouter(
        view: RootView,
        interactor: RootInteractor,
        component: RootBuilder.Component,
        private val feedBuilder: FeedBuilder,
        private val articleBuilder: ArticleBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    private var feedRouter: FeedRouter? = null
    private var articleRouter: ArticleRouter? = null

    fun attachFeed() = feedBuilder.build(view).let { router ->
        attachChild(router)
        view.addView(router.view)
        feedRouter = router
    }

    fun attachArticle(listItem: ListItem) = articleBuilder.build(view, listItem).let { router ->
        attachChild(router)
        view.addView(router.view)
        articleRouter = router
    }

    fun detachFeed() = feedRouter?.let { router ->
        detachChild(router)
        view.removeView(router.view)
        feedRouter = null
    }

    fun detachArticle() = articleRouter?.let { router ->
        detachChild(router)
        view.removeView(router.view)
        articleRouter = null
    }

}
