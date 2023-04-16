package com.stylingandroid.smoothscrolling;

import androidx.test.core.app.ApplicationProvider;
import io.reactivex.Observable;

public class TestStreamingUtil {

  public static Observable<ScrollEvent> streaming() {
    return ((AppPortal) ApplicationProvider.getApplicationContext())
        .appDependencies()
        .scrollEventStreaming()
        .streaming();
  }
}
