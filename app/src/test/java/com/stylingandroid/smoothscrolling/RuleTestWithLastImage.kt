package com.stylingandroid.smoothscrolling

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.RoborazziRule.CaptureType
import com.github.takahirom.roborazzi.RoborazziRule.Options
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class RuleTestWithLastImage : SchedulerBaseTest() {
  @get:Rule val roborazziRule = RoborazziRule(
    captureRoot = onView(isRoot()),
    options = Options(CaptureType.LastImage)
  )
  @Test
  fun captureRoboGifSample() {
    // launch
    launch(MainActivity::class.java)
    advanceTimer(100)
    triggerMainAction()
  }
}
