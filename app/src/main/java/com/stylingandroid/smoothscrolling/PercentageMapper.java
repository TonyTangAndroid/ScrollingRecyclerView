package com.stylingandroid.smoothscrolling;

import timber.log.Timber;

public class PercentageMapper {

  private PercentageMapper() {}

  public static int calculate(ScrolledItem child, TopLayoutSpec parent) {
    int childVisibleHeight = childVisibleHeight(child.screenRect(), parent);
    return childVisibleHeight >= child.viewHeight() ? 100 : percentage(child, childVisibleHeight);
  }

  private static int childVisibleHeight(RectEntity child, TopLayoutSpec parent) {
    if (parentCoverChild(child, parent)) {
      return child.bottom() - parent.screenRect().top();
    } else {
      return parent.screenRect().bottom() - child.top();
    }
  }

  private static boolean parentCoverChild(RectEntity child, TopLayoutSpec parent) {
    return parent.screenRect().bottom() > child.bottom();
  }

  private static int percentage(ScrolledItem item, int visibleHeight) {
    int percentage = visibleHeight * 100 / item.viewHeight();
    Timber.i("calculate_percentage:%s", percentage);
    return percentage;
  }
}
