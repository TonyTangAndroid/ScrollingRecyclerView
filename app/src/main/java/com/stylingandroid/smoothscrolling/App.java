package com.stylingandroid.smoothscrolling;

import android.app.Application;
import timber.log.Timber;

public final class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    TimberUtil.init(this);
    Timber.i("[tracing]:App onCreate:%s", this);
    Timber.i("[tracing]:dp:%s", DensityUtil.dp(this));
    Timber.i("[tracing]:px:%s", DensityUtil.px(this));
    Timber.i("[tracing]:density:%s", getResources().getDisplayMetrics().density);
  }
}
