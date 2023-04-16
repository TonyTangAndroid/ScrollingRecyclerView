package com.stylingandroid.smoothscrolling;

import io.reactivex.Observable;
import kotlin.Unit;

interface ScrollEventStreaming {

  Observable<ScrollEvent> streaming();

  Observable<ScrolledEvent> scrolledEvent();

  Observable<Unit> scrolledSignal();
}
