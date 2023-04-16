package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class TargetLayout {

  public abstract int firstPosition();

  public abstract int lastPosition();

  public abstract RectEntity rectEntity();

  public static Builder builder() {
    return new AutoValue_TargetLayout.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder firstPosition(int firstPosition);

    public abstract Builder lastPosition(int lastPosition);

    public abstract Builder rectEntity(RectEntity rectEntity);

    public abstract TargetLayout build();
  }
}
