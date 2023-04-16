package com.stylingandroid.smoothscrolling;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.google.common.truth.Truth.assertThat;

import androidx.test.core.app.ActivityScenario;
import io.reactivex.observers.TestObserver;
import org.junit.Ignore;
import org.junit.Test;

public class RecyclerViewAssertionsTest extends SchedulerBaseTest {

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

  @Ignore("does not work neither")
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
    onView(withId(R.id.recyclerview))
        .check(
            RecyclerViewAssertions.hasViewWithTextAtPosition(
                0, "position 0:100 percentage visible"));
  }

  private void assertTextViewNotRendered(MainActivity mainActivity) {
    onView(withId(R.id.recyclerview))
        .check(
            RecyclerViewAssertions.hasViewWithTextAtPosition(
                1, "position 1:100 percentage visible"));
  }
}
