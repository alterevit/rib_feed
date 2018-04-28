package com.example.ruslankushaliev.ribrecyclerview.rib.root.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ruslankushaliev.ribrecyclerview.R
import com.example.ruslankushaliev.ribrecyclerview.util.extension.inflate
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Scope

/**
 * Builder for the {@link FeedScope}.
 */
class FeedBuilder(dependency: ParentComponent) : ViewBuilder<FeedView, FeedRouter, FeedBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [FeedRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [FeedRouter].
     */
    fun build(parentViewGroup: ViewGroup): FeedRouter {
        val view = createView(parentViewGroup)
        val interactor = FeedInteractor()
        val component = DaggerFeedBuilder_Component.builder()
                .parentComponent(dependency)
                .view(view)
                .interactor(interactor)
                .build()
        return component.feedRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup) =
            parentViewGroup.inflate<FeedView>(R.layout.rib_feed)

    interface ParentComponent {
        val listener: FeedInteractor.Listener
    }

    @dagger.Module
    abstract class Module {

        @FeedScope
        @Binds
        internal abstract fun presenter(view: FeedView): FeedInteractor.FeedPresenter

        @dagger.Module
        companion object {

            @FeedScope
            @Provides
            @JvmStatic
            internal fun router(
                    component: Component,
                    view: FeedView,
                    interactor: FeedInteractor): FeedRouter =
                    FeedRouter(view, interactor, component)
        }

    }

    @FeedScope
    @dagger.Component(modules = [(Module::class)], dependencies = [(ParentComponent::class)])
    interface Component : InteractorBaseComponent<FeedInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: FeedInteractor): Builder

            @BindsInstance
            fun view(view: FeedView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun feedRouter(): FeedRouter
    }

    @Scope
    @Retention(AnnotationRetention.BINARY)
    internal annotation class FeedScope
}
