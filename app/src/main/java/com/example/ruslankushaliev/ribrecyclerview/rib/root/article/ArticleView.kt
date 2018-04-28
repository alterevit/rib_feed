package com.example.ruslankushaliev.ribrecyclerview.rib.root.article

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.example.ruslankushaliev.ribrecyclerview.rib.root.feed.enitity.ListItem
import kotlinx.android.synthetic.main.rib_article.view.*


/**
 * Top level view for {@link ArticleBuilder.ArticleScope}.
 */
class ArticleView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), ArticleInteractor.ArticlePresenter {

    override fun setListItem(listItem: ListItem) {
        tvTittle.text = listItem.text
    }

}
