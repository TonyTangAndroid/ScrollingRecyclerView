package com.stylingandroid.smoothscrolling;

import static com.google.common.truth.Truth.assertThat;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@Config(application = App.class)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  @Test
  public void case_0_whenActivityCreated_willStillNotReceiveEvent() {
    TestObserver<ScrollEvent> test = TestStreamingUtil.streaming().test();
    test.assertNoErrors();
    assertThat(test.valueCount()).isEqualTo(0);
    try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
      scenario.onActivity(activity -> assertStreamingEvent(test));
    }
  }

  private void assertStreamingEvent(TestObserver<ScrollEvent> test) {
    assertThat(test.valueCount()).isEqualTo(0);
  }
}
