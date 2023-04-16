package com.stylingandroid.smoothscrolling;

import android.app.Application;
import motif.ScopeFactory;
import timber.log.Timber;

public final class App extends Application implements AppPortal {

  private AppDependencies appDependencies;

  @Override
  public void onCreate() {
    super.onCreate();
    appDependencies = ScopeFactory.create(AppScope.class);
    TimberUtil.init(this);
    Timber.i("[tracing]:App onCreate:%s", this);
    Timber.i("[tracing]:dp:%s", DensityUtil.dp(this));
    Timber.i("[tracing]:px:%s", DensityUtil.px(this));
    Timber.i("[tracing]:density:%s", getResources().getDisplayMetrics().density);
  }

  @Override
  public AppDependencies appDependencies() {
    return appDependencies;
  }
}
