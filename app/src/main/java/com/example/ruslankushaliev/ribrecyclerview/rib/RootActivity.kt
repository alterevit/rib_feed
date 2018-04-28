package com.example.ruslankushaliev.ribrecyclerview.rib

import android.view.ViewGroup
import com.example.ruslankushaliev.ribrecyclerview.rib.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class RootActivity : RibActivity() {

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
            RootBuilder(object : RootBuilder.ParentComponent {}).build(parentViewGroup)
}