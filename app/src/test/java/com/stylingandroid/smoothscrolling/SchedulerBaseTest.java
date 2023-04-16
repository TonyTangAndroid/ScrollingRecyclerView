package com.stylingandroid.smoothscrolling;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@Config(application = App.class)
@RunWith(AndroidJUnit4.class)
public abstract class SchedulerBaseTest {

  private final TestScheduler ioTestScheduler = new TestScheduler();
  private final TestScheduler cpuTestScheduler = new TestScheduler();
  private final TestScheduler mainTestScheduler = new TestScheduler();

  @Before
  public void setUp() {
    RxJavaPlugins.setIoSchedulerHandler(ignored -> ioTestScheduler);
    RxJavaPlugins.setComputationSchedulerHandler(ignored -> cpuTestScheduler);
    RxAndroidPlugins.setMainThreadSchedulerHandler(ignored -> mainTestScheduler);
  }

  protected void advanceTimer(int durationInMs) {
    cpuTestScheduler.advanceTimeTo(durationInMs, TimeUnit.MILLISECONDS);
  }

  protected void triggerIoAction() {
    ioTestScheduler.triggerActions();
  }

  protected void triggerMainAction() {
    mainTestScheduler.triggerActions();
  }
}
