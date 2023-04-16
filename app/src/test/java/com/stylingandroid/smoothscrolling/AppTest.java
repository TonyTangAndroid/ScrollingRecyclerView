package com.stylingandroid.smoothscrolling;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.common.truth.Truth;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@Config(application = App.class)
@RunWith(AndroidJUnit4.class)
public class AppTest {

  @Test
  public void case_01_AppContextIsAppClass() {
    Context context = ApplicationProvider.getApplicationContext();
    Truth.assertThat(context).isInstanceOf(App.class);
  }

  @Test
  public void case_02_AppContextIsAppPortal() {
    Context context = ApplicationProvider.getApplicationContext();
    Truth.assertThat(context).isInstanceOf(AppPortal.class);
  }

  @Test
  public void case_03_AppContextIsAppPortal() {
    TestObserver<ScrollEvent> test = TestStreamingUtil.streaming().test();
    test.assertNoErrors();
  }
}
