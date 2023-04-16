package com.stylingandroid.smoothscrolling;

import android.graphics.Rect;

public class RectEntityMapper {

  public static RectEntity map(Rect rect) {
    return RectEntity.create(rect.left, rect.top, rect.right, rect.bottom);
  }
}
