package com.stylingandroid.smoothscrolling;

import androidx.recyclerview.widget.RecyclerView;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ScrolledEvent {

  public abstract Delta delta();

  public abstract RecyclerView target();

  public static ScrolledEvent create(Delta delta, RecyclerView target) {
    return new AutoValue_ScrolledEvent(delta, target);
  }
}
