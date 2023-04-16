package com.stylingandroid.smoothscrolling;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.collect.ImmutableMap;
import com.jakewharton.rxrelay2.PublishRelay;
import com.stylingandroid.smoothscrolling.ScrollEvent.Type;
import com.stylingandroid.smoothscrolling.ScrollingEvent.State;
import io.reactivex.Observable;
import java.util.Optional;
import kotlin.Unit;

class PublishableOnScrollListener extends AppOnScrollListener implements ScrollEventStreaming {

  private static final ImmutableMap<Integer, State> map =
      ImmutableMap.of(
          0, State.IDLE,
          1, State.DRAGGING,
          2, State.SETTLING);

  private final PublishRelay<ScrollEvent> publishRelay = PublishRelay.create();

  @Override
  public void onScrollStateChanged(@NonNull RecyclerView target, int newState) {
    publishRelay.accept(ScrollEvent.ofScrolling(scrollingEvent(newState, target)));
  }

  private ScrollingEvent scrollingEvent(int newState, RecyclerView target) {
    return ScrollingEvent.create(state(newState), target);
  }

  private State state(int rawState) {
    return Optional.ofNullable(map.get(rawState)).orElse(State.UNKNOWN);
  }

  @Override
  public void onScrolled(@NonNull RecyclerView target, int dx, int dy) {
    publishRelay.accept(ScrollEvent.ofScrolled(ScrolledEvent.create(Delta.create(dx, dy), target)));
  }

  @Override
  public Observable<ScrollEvent> streaming() {
    return publishRelay.hide();
  }

  @Override
  public Observable<ScrolledEvent> scrolledEvent() {
    return streaming().filter(item -> Type.SCROLLED.equals(item.type())).map(ScrollEvent::scrolled);
  }

  @Override
  public Observable<Unit> scrolledSignal() {
    return scrolledEvent().map(ignored -> Unit.INSTANCE);
  }
}
