package com.stylingandroid.smoothscrolling;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import timber.log.Timber;

public class ScrolledModelMapper {

  private ScrolledModelMapper() {}

  public static ScrolledModel assemble(ScrolledEvent event) {
    LayoutManager layoutManager = event.target().getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      return map(event.target(), (LinearLayoutManager) layoutManager);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  private static ScrolledModel map(RecyclerView target, LinearLayoutManager layoutManager) {
    if (layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
      Timber.i("Only support vertical layout manager for now.");
      return ScrolledModel.create(ImmutableList.of(), null);
    } else {
      return mapInternal(layoutManager, topLayoutSpec(target, layoutManager));
    }
  }

  private static ScrolledModel mapInternal(
      LinearLayoutManager layoutManager, TopLayoutSpec topLayoutSpec) {
    return ScrolledModel.create(scrolledItemList(layoutManager, topLayoutSpec), topLayoutSpec);
  }

  private static List<ScrolledItem> scrolledItemList(
      LinearLayoutManager layoutManager, TopLayoutSpec targetLayout) {
    return IntStream.rangeClosed(targetLayout.firstPosition(), targetLayout.lastPosition())
        .mapToObj(position -> toItem(position, layoutManager))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  private static Optional<ScrolledItem> toItem(int position, LinearLayoutManager manager) {
    return Optional.ofNullable(manager.findViewByPosition(position))
        .map(item -> renderedItem(item, position));
  }

  private static ScrolledItem renderedItem(View item, int adapterPosition) {
    return ScrolledItem.create(adapterPosition, rectEntity(item), item.getHeight());
  }

  private static TopLayoutSpec topLayoutSpec(
      RecyclerView target, LinearLayoutManager layoutManager) {
    return TopLayoutSpec.builder()
        .firstPosition(layoutManager.findFirstVisibleItemPosition())
        .lastPosition(layoutManager.findLastVisibleItemPosition())
        .screenRect(rectEntity(target))
        .build();
  }

  private static RectEntity rectEntity(View target) {
    Rect rect = new Rect();
    target.getGlobalVisibleRect(rect);
    return RectEntityMapper.map(rect);
  }
}
