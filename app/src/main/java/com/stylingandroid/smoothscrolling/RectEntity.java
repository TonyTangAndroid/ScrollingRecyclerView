package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RectEntity {

  public abstract int left();

  public abstract int top();

  public abstract int right();

  public abstract int bottom();

  public static RectEntity create(int left, int top, int right, int bottom) {
    return new AutoValue_RectEntity(left, top, right, bottom);
  }
}
