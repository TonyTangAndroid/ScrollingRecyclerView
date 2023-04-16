package com.stylingandroid.smoothscrolling;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class DeviceSpec {

  public abstract int width();

  public abstract int height();

  public abstract Unit unit();

  public static Builder builder() {
    return new AutoValue_DeviceSpec.Builder();
  }

  public enum Unit {
    DP,
    PX
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder width(int width);

    public abstract Builder height(int height);

    public abstract Builder unit(Unit unit);

    public abstract DeviceSpec build();
  }
}
