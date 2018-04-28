package com.example.ruslankushaliev.ribrecyclerview.rib.root.feed

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link FeedBuilder.FeedScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class FeedRouter(
    view: FeedView,
    interactor: FeedInteractor,
    component: FeedBuilder.Component) : ViewRouter<FeedView, FeedInteractor, FeedBuilder.Component>(view, interactor, component)
