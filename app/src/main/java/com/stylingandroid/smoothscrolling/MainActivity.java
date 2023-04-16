package com.stylingandroid.smoothscrolling;

import static com.uber.autodispose.AutoDispose.autoDisposable;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.Observable;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private LargeAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Timber.i("[tracing]:MainActivity onCreate:%s", this);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.recyclerview);
    ScrollingLinearLayoutManager layout = layoutManager();
    layout.setInitialPrefetchItemCount(3);
    recyclerView.setLayoutManager(layout);
    recyclerView.addOnScrollListener(appDependencies().publishableOnScrollListener());
    adapter = LargeAdapter.newInstance(this);
    recyclerView.setAdapter(adapter);
    observeScrollEventToLog();
    observeScrolledEventToRefreshItem();
  }

  private void observeScrolledEventToRefreshItem() {
    throttleScrolledEvent()
        .as(autoDisposable(AndroidLifecycleScopeProvider.from(this)))
        .subscribe(this::onScrolled);
  }

  private Observable<ScrolledEvent> throttleScrolledEvent() {
    return appDependencies().derivedStreaming().throttledScrolledEvent();
  }

  private void onScrolled(ScrolledEvent event) {
    ScrolledModel assemble = ScrolledModelMapper.assemble(event);
    assemble.list().forEach(this::renderItem);
  }

  private void renderItem(ScrolledItem item) {
    ItemData itemData = adapter.items().get(item.position());
    if (itemData.percentage() != item.percentage()) {
      itemData.setPercentage(item.percentage());
      adapter.notifyItemChanged(item.position());
    }
  }

  private void observeScrollEventToLog() {
    appDependencies()
        .scrollEventStreaming()
        .streaming()
        .as(autoDisposable(AndroidLifecycleScopeProvider.from(this)))
        .subscribe(this::onEventEmitted);
  }

  private void onEventEmitted(ScrollEvent scrollEvent) {
    Timber.i("[ScrollEvent]:%s", scrollEvent);
  }

  private ScrollingLinearLayoutManager layoutManager() {
    return new ScrollingLinearLayoutManager(
        this,
        LinearLayoutManager.VERTICAL,
        false,
        getResources().getInteger(R.integer.scroll_duration));
  }

  private AppDependencies appDependencies() {
    return ((AppPortal) getApplication()).appDependencies();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_top) {
      recyclerView.smoothScrollToPosition(0);
      return true;
    } else if (id == R.id.action_bottom) {
      recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onResume() {
    super.onResume();
    Timber.i("[tracing]:MainActivity onResume:%s", this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    Timber.i("[tracing]:MainActivity onPause:%s", this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    Timber.i("[tracing]:MainActivity onStop:%s", this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    Timber.i("[tracing]:MainActivity onStart:%s", this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Timber.i("[tracing]:MainActivity onDestroy:%s", this);
  }
}
