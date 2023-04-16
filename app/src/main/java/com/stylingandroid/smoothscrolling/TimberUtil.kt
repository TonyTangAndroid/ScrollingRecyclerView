package com.stylingandroid.smoothscrolling;

import com.tonytangandroid.wood.WoodTree
import timber.log.Timber
import timber.log.Timber.Forest.plant

class TimberUtil {
    companion object Factory {
        @JvmStatic fun init(context: App) {
            val woodTree = WoodTree(context)
            plant(Timber.DebugTree())
            plant(woodTree)
        }
    }
}
