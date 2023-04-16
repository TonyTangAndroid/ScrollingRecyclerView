package com.stylingandroid.smoothscrolling;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;

public class ScrolledModelMapper {

  private ScrolledModelMapper() {}

  public static ScrolledModel assemble(ScrolledEvent event) {
    RecyclerView target = event.target();
    LayoutManager layoutManager = target.getLayoutManager();
    if (!(layoutManager instanceof LinearLayoutManager)) {
      return ScrolledModel.create(ImmutableList.of());
    } else {
      return map(target, (LinearLayoutManager) layoutManager);
    }
  }

  private static ScrolledModel map(RecyclerView target, LinearLayoutManager layoutManager) {
    if (layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
      Timber.i("Only support vertical layout manager for now.");
      return ScrolledModel.create(ImmutableList.of());
    } else {
      return mapInternal(target, layoutManager);
    }
  }

  private static ScrolledModel mapInternal(RecyclerView target, LinearLayoutManager layoutManager) {
    TargetLayout targetLayout = targetLayout(target, layoutManager);
    int bottom = targetLayout.rectEntity().bottom();
    int top = targetLayout.rectEntity().top();
    List<ScrolledItem> list = new ArrayList<>();
    for (int i = targetLayout.firstPosition(); i <= targetLayout.lastPosition(); i++) {
      View itemView = layoutManager.findViewByPosition(i);
      if (itemView == null) {
        continue;
      }
      list.add(getScrolledItem(bottom, top, i, itemView));
    }
    return ScrolledModel.create(ImmutableList.copyOf(list));
  }

  private static ScrolledItem getScrolledItem(int bottom, int top, int i, View itemView) {
    RectEntity rowRect = rectEntity(itemView);
    int percentFirst;
    int visibleHeightFirst;
    if (rowRect.bottom() >= bottom) {
      visibleHeightFirst = bottom - rowRect.top();
    } else {
      visibleHeightFirst = rowRect.bottom() - top;
    }
    int height = itemView.getHeight();
    percentFirst = Math.min(100, visibleHeightFirst * 100 / height);
    ScrolledItem e = ScrolledItem.create(percentFirst, i);
    return e;
  }

  private static TargetLayout targetLayout(RecyclerView target, LinearLayoutManager layoutManager) {
    int firstPosition = layoutManager.findFirstVisibleItemPosition();
    int lastPosition = layoutManager.findLastVisibleItemPosition();
    RectEntity rectEntity = rectEntity(target);
    return TargetLayout.builder()
        .firstPosition(firstPosition)
        .lastPosition(lastPosition)
        .rectEntity(rectEntity)
        .build();
  }

  private static RectEntity rectEntity(View target) {
    Rect rect = new Rect();
    target.getGlobalVisibleRect(rect);
    return RectEntityMapper.map(rect);
  }
}
