package com.stylingandroid.smoothscrolling;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.common.truth.Truth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@Config(application = App.class)
@RunWith(AndroidJUnit4.class)
public class AppTest {

  @Test
  public void test123() {
    Context context = ApplicationProvider.getApplicationContext();
    Truth.assertThat(context).isInstanceOf(App.class);
  }
}
