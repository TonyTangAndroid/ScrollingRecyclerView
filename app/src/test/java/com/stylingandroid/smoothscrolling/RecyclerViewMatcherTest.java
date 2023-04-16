package com.stylingandroid.smoothscrolling;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;
import static com.stylingandroid.smoothscrolling.RecyclerViewMatcher.withRecyclerView;

import android.view.View;
import androidx.test.core.app.ActivityScenario;
import io.reactivex.observers.TestObserver;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;

/** This is only to assert that the espresso test somehow only could assert the first item. */
public class RecyclerViewMatcherTest extends SchedulerBaseTest {

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

  @Ignore("not working")
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

    onView(withRecyclerView(R.id.recyclerview).atPosition(0))
        .check(matches(hasDescendant(withText("position 0:100 percentage visible"))));
  }

  private void assertTextViewNotRendered(MainActivity mainActivity) {
    RecyclerViewMatcher recyclerViewMatcher = withRecyclerView(R.id.recyclerview);
    Matcher<View> viewMatcher = recyclerViewMatcher.atPosition(1);
    onView(viewMatcher)
        .check(matches(hasDescendant(withText("position 1:100 percentage visible"))));
  }
}
