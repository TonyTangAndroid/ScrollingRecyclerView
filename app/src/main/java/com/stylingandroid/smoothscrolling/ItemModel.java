package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ItemModel {

  public abstract int position();

  public abstract ItemData data();

  public static ItemModel create(int position, ItemData data) {
    return new AutoValue_ItemModel(position, data);
  }
}
