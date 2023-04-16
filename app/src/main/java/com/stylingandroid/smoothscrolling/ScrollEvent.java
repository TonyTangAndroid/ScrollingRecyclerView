package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoOneOf;

@AutoOneOf(ScrollEvent.Type.class)
public abstract class ScrollEvent {

  public abstract Type type();

  public abstract ScrolledEvent scrolled();

  public abstract ScrollingEvent scrolling();

  public static ScrollEvent ofScrolling(ScrollingEvent event) {
    return AutoOneOf_ScrollEvent.scrolling(event);
  }

  public static ScrollEvent ofScrolled(ScrolledEvent event) {
    return AutoOneOf_ScrollEvent.scrolled(event);
  }

  public enum Type {
    SCROLLING,
    SCROLLED,
  }
}
