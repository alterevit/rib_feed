package com.example.ruslankushaliev.ribrecyclerview.rib.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ruslankushaliev.ribrecyclerview.R
import com.example.ruslankushaliev.ribrecyclerview.rib.root.article.ArticleBuilder
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.FeedBuilder
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.FeedInteractor
import com.example.ruslankushaliev.ribrecyclerview.util.extension.inflate
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Scope

/**
 * Builder for the {@link RootScope}.
 */
class RootBuilder(dependency: ParentComponent) : ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [RootRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [RootRouter].
     */
    fun build(parentViewGroup: ViewGroup): RootRouter {
        val view = createView(parentViewGroup)
        val interactor = RootInteractor()
        val component = DaggerRootBuilder_Component.builder()
                .parentComponent(dependency)
                .view(view)
                .interactor(interactor)
                .build()
        return component.rootRouter()

    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView =
            parentViewGroup.inflate<RootView>(R.layout.rib_root)

    interface ParentComponent

    @dagger.Module
    abstract class Module {

        @RootScope
        @Binds
        internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

        @dagger.Module
        companion object {

            @RootScope
            @Provides
            @JvmStatic
            internal fun router(
                    component: Component,
                    view: RootView,
                    interactor: RootInteractor): RootRouter {
                return RootRouter(view, interactor, component, FeedBuilder(component), ArticleBuilder(component))
            }

            @RootScope
            @Provides
            @JvmStatic
            internal fun feedInteractorListener(interactor: RootInteractor): FeedInteractor.Listener =
                    interactor.FeedInteractorListener()
        }

    }

    @RootScope
    @dagger.Component(modules = [(Module::class)], dependencies = [(ParentComponent::class)])
    interface Component : InteractorBaseComponent<RootInteractor>,
            FeedBuilder.ParentComponent,
            ArticleBuilder.ParentComponent,
            BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RootInteractor): Builder

            @BindsInstance
            fun view(view: RootView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun rootRouter(): RootRouter
    }

    @Scope
    @Retention(AnnotationRetention.BINARY)
    internal annotation class RootScope

}
