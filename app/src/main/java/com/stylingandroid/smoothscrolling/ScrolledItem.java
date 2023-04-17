package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ScrolledItem {

  public abstract int adapterPosition();

  public abstract RectEntity screenRect();

  public abstract int viewHeight();

  public static ScrolledItem create(int adapterPosition, RectEntity rectEntity, int viewHeight) {
    return new AutoValue_ScrolledItem(adapterPosition, rectEntity, viewHeight);
  }
}
