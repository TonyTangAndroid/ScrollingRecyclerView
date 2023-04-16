package com.stylingandroid.smoothscrolling;

import android.app.Application;

public final class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    TimberUtil.init(this);
  }
}
