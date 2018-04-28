package com.example.ruslankushaliev.ribrecyclerview.rib.root.article

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ArticleBuilder.ArticleScope}.
 */
class ArticleRouter(
    view: ArticleView,
    interactor: ArticleInteractor,
    component: ArticleBuilder.Component) : ViewRouter<ArticleView, ArticleInteractor, ArticleBuilder.Component>(view, interactor, component)
