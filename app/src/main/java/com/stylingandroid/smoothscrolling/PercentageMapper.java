package com.stylingandroid.smoothscrolling;

import timber.log.Timber;

public class PercentageMapper {

  private PercentageMapper() {}

  public static int calculate(ScrolledItem child, TopLayoutSpec parent) {
    int heightOnScreen = heightOnScreen(child, parent);
    return heightOnScreen >= child.viewHeight() ? 100 : percentage(child, heightOnScreen);
  }

  /**
   * There are 2 scenarios when the children's view overlaps with the parent view on the screen.
   *
   * <p>Case 1: Compared to the parent view, the children view is scrolled off a bit from top to
   * bottom and its remaining part is still visible on the screen. In this case, the height is the
   * delta between the parent's top to the child's bottom.
   *
   * <p>Case 1: Compared to the parent view, the children view is pending to be fully shown as the
   * top part of the child is visible , yet there is remaining part to be shown. In this case, the
   * height is the delta between the child's top to the parent's bottom.
   */
  private static int heightOnScreen(ScrolledItem childItem, TopLayoutSpec parent) {
    RectEntity childRect = childItem.screenRect();
    RectEntity parentRect = parent.screenRect();
    log(childRect, parentRect);
    if (parentRect.bottom() > childRect.bottom()) {
      return childRect.bottom() - parentRect.top();
    } else {
      return parentRect.bottom() - childRect.top();
    }
  }

  private static void log(RectEntity childRect, RectEntity parentRect) {
    Timber.i(
        "[raw_spec][child]:[top:%s,bottom:%s],[parent]:[top:%s,bottom:%s]",
        childRect.top(), childRect.bottom(), parentRect.top(), parentRect.bottom());
  }

  private static int percentage(ScrolledItem item, int visibleHeight) {
    int percentage = visibleHeight * 100 / item.viewHeight();
    Timber.i("calculate_percentage:%s", percentage);
    return percentage;
  }
}
