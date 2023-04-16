package com.stylingandroid.smoothscrolling;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;

import androidx.test.core.app.ActivityScenario;
import io.reactivex.observers.TestObserver;
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Does not work at all")
public class RecyclerViewChildActionsTest extends SchedulerBaseTest {

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
            matches(
                RecyclerViewChildActions.Companion.childOfViewAtPositionWithMatcher(
                    R.id.tv_percentage, 0, withText("position 0:100 percentage visible"))));
  }

  private void assertTextViewNotRendered(MainActivity mainActivity) {
    onView(withId(R.id.recyclerview))
        .check(
            matches(
                RecyclerViewChildActions.Companion.childOfViewAtPositionWithMatcher(
                    R.id.tv_percentage, 1, withText("position 1:100 percentage visible"))));
  }
}
