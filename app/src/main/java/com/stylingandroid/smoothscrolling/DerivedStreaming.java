package com.stylingandroid.smoothscrolling;

import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class DerivedStreaming {

  private final ScrollEventStreaming scrollEventStreaming;

  public DerivedStreaming(ScrollEventStreaming scrollEventStreaming) {
    this.scrollEventStreaming = scrollEventStreaming;
  }

  public Observable<ScrolledEvent> throttledScrolledEvent() {
    return scrollEventStreaming.scrolledEvent().throttleLast(100, TimeUnit.MILLISECONDS);
  }
}
