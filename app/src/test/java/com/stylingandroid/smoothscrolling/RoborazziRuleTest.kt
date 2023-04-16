package com.stylingandroid.smoothscrolling

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode
@Ignore("xxx")
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(AndroidJUnit4::class)
class RoborazziRuleTest {
    @get:Rule
    val roborazziRule = RoborazziRule(onView(isRoot()))

    @Test
    fun captureRoboGifSample() {
        launch(MainActivity::class.java)
        onView(isRoot()).captureRoboImage()
    }
}