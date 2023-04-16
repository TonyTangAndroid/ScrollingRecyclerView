package com.stylingandroid.smoothscrolling;

import static com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition;
import static com.google.common.truth.Truth.assertThat;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

/** This is only to assert that the espresso test somehow only could assert the first item. */
public class RecyclerViewChildrenCountTest extends SchedulerBaseTest {

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

  private void assertTextViewRendered(MainActivity mainActivity) {
    advanceTimer(1000);
    assertDisplayedAtPosition(R.id.recyclerview, 0, "position 0:100 percentage visible");
    RecyclerView recyclerView = mainActivity.findViewById(R.id.recyclerview);
    int childCount = recyclerView.getChildCount();
    assertThat(childCount).isEqualTo(2);
  }
}
