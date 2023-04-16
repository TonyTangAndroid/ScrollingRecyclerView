package com.stylingandroid.smoothscrolling;

import motif.Creatable;
import motif.NoDependencies;

@motif.Scope
public interface AppScope extends Creatable<NoDependencies>, AppDependencies {

  @motif.Objects
  abstract class Objects implements AppObjects {}

  interface AppObjects {

    PublishableOnScrollListener PublishableOnScrollListener();

    ScrollEventStreaming scrollEventStreaming(PublishableOnScrollListener impl);

    AppOnScrollListener appOnScrollListener(PublishableOnScrollListener impl);
  }
}
