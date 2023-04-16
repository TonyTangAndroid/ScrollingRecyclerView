package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ScrolledItem {

  public abstract int percentage();

  public abstract int position();

  public static ScrolledItem create(int percentage, int position) {
    return new AutoValue_ScrolledItem(percentage, position);
  }
}
