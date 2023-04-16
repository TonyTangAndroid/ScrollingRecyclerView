package com.stylingandroid.smoothscrolling;

import io.reactivex.Observable;

interface ScrollEventStreaming {

  Observable<ScrollEvent> streaming();
}
