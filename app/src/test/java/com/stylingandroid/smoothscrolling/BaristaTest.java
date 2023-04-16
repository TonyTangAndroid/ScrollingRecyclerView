package com.stylingandroid.smoothscrolling;

import static com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.google.common.truth.Truth.assertThat;

import androidx.test.core.app.ActivityScenario;
import io.reactivex.observers.TestObserver;
import org.junit.Ignore;
import org.junit.Test;

/** This is only to assert that the espresso test somehow only could assert the first item. */
public class BaristaTest extends SchedulerBaseTest {

  @Test
  public void case_0_position_0_itWillWork() {
    TestObserver<ScrollEvent> test = TestStreamingUtil.streaming().test();
    test.assertNoErrors();
    assertThat(test.valueCount()).isEqualTo(0);
    try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
      advanceTimer(100);
      triggerMainAction();
      scenario.onActivity(this::assertTextViewRendered);
    }
  }

  @Ignore("NoMatchingViewException")
  @Test
  public void case_1_position_1_itWillNotWork() {
    TestObserver<ScrollEvent> test = TestStreamingUtil.streaming().test();
    test.assertNoErrors();
    assertThat(test.valueCount()).isEqualTo(0);
    try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
      advanceTimer(100);
      triggerMainAction();
      scenario.onActivity(this::assertTextViewNotRendered);
    }
  }

  private void assertTextViewRendered(MainActivity mainActivity) {
    assertDisplayedAtPosition(R.id.recyclerview, 0, "position 0:100 percentage visible");
    assertDisplayed("position 0:100 percentage visible");
  }

  private void assertTextViewNotRendered(MainActivity mainActivity) {
    assertDisplayed("position 1:100 percentage visible");
  }
}
