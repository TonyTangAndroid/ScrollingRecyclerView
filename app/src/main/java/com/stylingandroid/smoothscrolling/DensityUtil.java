package com.stylingandroid.smoothscrolling;

import android.content.Context;
import android.util.DisplayMetrics;
import com.stylingandroid.smoothscrolling.DeviceSpec.Unit;

public class DensityUtil {

  public static DeviceSpec dp(Context context) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
    float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
    return DeviceSpec.builder().height((int) dpHeight).width((int) dpWidth).unit(Unit.DP).build();
  }

  public static DeviceSpec px(Context context) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return DeviceSpec.builder()
        .height(displayMetrics.heightPixels)
        .width(displayMetrics.widthPixels)
        .unit(Unit.PX)
        .build();
  }
}
