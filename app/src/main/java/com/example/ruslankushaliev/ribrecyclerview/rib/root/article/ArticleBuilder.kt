package com.example.ruslankushaliev.ribrecyclerview.rib.root.article

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ruslankushaliev.ribrecyclerview.R
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.enitity.ListItem
import com.example.ruslankushaliev.ribrecyclerview.util.extension.inflate
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link ArticleScope}.
 */
class ArticleBuilder(dependency: ParentComponent) : ViewBuilder<ArticleView, ArticleRouter, ArticleBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [ArticleRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [ArticleRouter].
     */
    fun build(parentViewGroup: ViewGroup, listItem: ListItem): ArticleRouter {
        val view = createView(parentViewGroup)
        val interactor = ArticleInteractor()
        val component = DaggerArticleBuilder_Component.builder()
                .parentComponent(dependency)
                .view(view)
                .interactor(interactor)
                .listItem(listItem)
                .build()
        return component.articleRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup) =
            parentViewGroup.inflate<ArticleView>(R.layout.rib_article)

    interface ParentComponent

    @dagger.Module
    abstract class Module {

        @ArticleScope
        @Binds
        internal abstract fun presenter(view: ArticleView): ArticleInteractor.ArticlePresenter

        @dagger.Module
        companion object {

            @ArticleScope
            @Provides
            @JvmStatic
            internal fun router(
                    component: Component,
                    view: ArticleView,
                    interactor: ArticleInteractor): ArticleRouter {
                return ArticleRouter(view, interactor, component)
            }

        }

    }

    @ArticleScope
    @dagger.Component(modules = [(Module::class)], dependencies = [(ParentComponent::class)])
    interface Component : InteractorBaseComponent<ArticleInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {

            @BindsInstance
            fun interactor(interactor: ArticleInteractor): Builder

            @BindsInstance
            fun view(view: ArticleView): Builder

            @BindsInstance
            fun listItem(listItem: ListItem): Builder

            fun parentComponent(component: ParentComponent): Builder

            fun build(): Component

        }
    }

    interface BuilderComponent {
        fun articleRouter(): ArticleRouter
    }

    @Scope
    @Retention(AnnotationRetention.BINARY)
    internal annotation class ArticleScope

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class ArticleInternal

}
