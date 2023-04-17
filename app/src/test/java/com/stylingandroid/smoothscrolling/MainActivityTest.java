package com.stylingandroid.smoothscrolling;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;

import androidx.test.core.app.ActivityScenario;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

public class MainActivityTest extends SchedulerBaseTest {

  @Test
  public void case_0_whenActivityCreated_willReceiveEvent() {
    TestObserver<ScrollEvent> test = TestStreamingUtil.streaming().test();
    test.assertNoErrors();
    assertThat(test.valueCount()).isEqualTo(0);
    try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
      scenario.onActivity(activity -> assertStreamingEvent(test));
    }
  }

  @Test
  public void case_1_whenTimePassed_willRenderPercentage() {
    TestObserver<ScrollEvent> test = TestStreamingUtil.streaming().test();
    test.assertNoErrors();
    assertThat(test.valueCount()).isEqualTo(0);
    try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
      advanceTimer(100);
      triggerMainAction();
      scenario.onActivity(this::assertTextViewRendered);
    }
  }

  @Test
  public void case_2_whenTimeNotPassed_willNotRenderPercentage() {
    TestObserver<ScrollEvent> test = TestStreamingUtil.streaming().test();
    test.assertNoErrors();
    assertThat(test.valueCount()).isEqualTo(0);
    try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
      advanceTimer(90);
      triggerMainAction();
      scenario.onActivity(this::assertTextViewNotRendered);
    }
  }

  @Test
  public void case_3_whenActionNotTriggered_willNotRenderPercentage() {
    TestObserver<ScrollEvent> test = TestStreamingUtil.streaming().test();
    test.assertNoErrors();
    assertThat(test.valueCount()).isEqualTo(0);
    try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
      advanceTimer(100);
      //      triggerMainAction();
      scenario.onActivity(this::assertTextViewNotRendered);
    }
  }

  private void assertStreamingEvent(TestObserver<ScrollEvent> test) {
    assertThat(test.valueCount()).isGreaterThan(0);
  }

  private void assertTextViewRendered(MainActivity mainActivity) {
    onView(withText("position:0,percentage:100")).check(matches(isDisplayed()));
    //    onView(withText("position:1,percentage:100")).check(matches(isDisplayed()));
  }

  private void assertTextViewNotRendered(MainActivity mainActivity) {
    onView(withText("position:0,percentage:100")).check(doesNotExist());
    //    onView(withText("position:1,percentage:100")).check(matches(isDisplayed()));
  }
}
