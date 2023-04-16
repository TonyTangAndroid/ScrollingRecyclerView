package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Delta {

  public abstract int dx();

  public abstract int dy();

  public static Delta create(int dx, int dy) {
    return new AutoValue_Delta(dx, dy);
  }
}
