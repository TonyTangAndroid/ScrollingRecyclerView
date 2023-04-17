package com.stylingandroid.smoothscrolling;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;

public class DerivedStreaming {

  private final ScrollEventStreaming scrollEventStreaming;

  public DerivedStreaming(ScrollEventStreaming scrollEventStreaming) {
    this.scrollEventStreaming = scrollEventStreaming;
  }

  public Observable<ScrolledEvent> throttledScrolledEvent() {
    return scrollEventStreaming.scrolledEvent().throttleLast(100, TimeUnit.MILLISECONDS);
  }

  public Observable<ScrolledModel> scrolledModel() {
    return throttledScrolledEvent()
        .observeOn(AndroidSchedulers.mainThread())
        .map(ScrolledModelMapper::assemble);
  }
}
