package com.stylingandroid.smoothscrolling;

import androidx.recyclerview.widget.RecyclerView;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ScrollingEvent {

  public abstract State state();

  public abstract RecyclerView target();

  public static ScrollingEvent create(State state, RecyclerView target) {
    return new AutoValue_ScrollingEvent(state, target);
  }

  public enum State {
    /** The RecyclerView is not currently scrolling. */
    IDLE,
    /** The RecyclerView is currently being dragged by outside input such as user touch input. */
    DRAGGING,
    /**
     * The RecyclerView is currently animating to a final position while not under outside control.
     */
    SETTLING,
  }
}
