package com.stylingandroid.smoothscrolling;

public interface AppDependencies {
  DerivedStreaming derivedStreaming();

  ScrollEventStreaming scrollEventStreaming();

  PublishableOnScrollListener publishableOnScrollListener();
}
