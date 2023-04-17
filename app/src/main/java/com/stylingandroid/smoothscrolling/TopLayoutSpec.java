package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class TopLayoutSpec {

  public abstract int firstPosition();

  public abstract int lastPosition();

  public abstract RectEntity screenRect();

  public static Builder builder() {
    return new AutoValue_TopLayoutSpec.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder firstPosition(int firstPosition);

    public abstract Builder lastPosition(int lastPosition);

    public abstract Builder screenRect(RectEntity rectEntity);

    public abstract TopLayoutSpec build();
  }
}
