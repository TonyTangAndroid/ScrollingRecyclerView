package com.stylingandroid.smoothscrolling;

import android.app.Application;
import timber.log.Timber;

public final class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    TimberUtil.init(this);
    Timber.i("App onCreate:%s", this);
  }
}
