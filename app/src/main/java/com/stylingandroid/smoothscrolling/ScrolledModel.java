package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
public abstract class ScrolledModel {

  public abstract List<ScrolledItem> list();

  public static ScrolledModel create(List<ScrolledItem> list) {
    return new AutoValue_ScrolledModel(list);
  }
}
