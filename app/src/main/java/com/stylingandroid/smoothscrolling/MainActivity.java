package com.stylingandroid.smoothscrolling;

import static com.uber.autodispose.AutoDispose.autoDisposable;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Timber.i("[tracing]:MainActivity onCreate:%s", this);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.recyclerview);
    recyclerView.setLayoutManager(layoutManager());
    recyclerView.addOnScrollListener(appDependencies().publishableOnScrollListener());
    recyclerView.setAdapter(LargeAdapter.newInstance(this));
    observeScrollEventToLog();
    observeScrolledEventToRefreshItem();
  }

  private void observeScrolledEventToRefreshItem() {
    appDependencies()
        .scrollEventStreaming()
        .scrolledEvent()
        .throttleLast(100, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .as(autoDisposable(AndroidLifecycleScopeProvider.from(this)))
        .subscribe(this::onScrolled);
  }

  private void onScrolled(ScrolledEvent event) {
    RecyclerView rvPercentage = event.target();
    LinearLayoutManager layoutManager = ((LinearLayoutManager) rvPercentage.getLayoutManager());
    final int firstPosition = layoutManager.findFirstVisibleItemPosition();
    final int lastPosition = layoutManager.findLastVisibleItemPosition();
    Rect rvRect = new Rect();
    rvPercentage.getGlobalVisibleRect(rvRect);
    for (int i = firstPosition; i <= lastPosition; i++) {
      Rect rowRect = new Rect();
      layoutManager.findViewByPosition(i).getGlobalVisibleRect(rowRect);

      int percentFirst;
      if (rowRect.bottom >= rvRect.bottom) {
        int visibleHeightFirst = rvRect.bottom - rowRect.top;
        percentFirst = (visibleHeightFirst * 100) / layoutManager.findViewByPosition(i).getHeight();
      } else {
        int visibleHeightFirst = rowRect.bottom - rvRect.top;
        percentFirst = (visibleHeightFirst * 100) / layoutManager.findViewByPosition(i).getHeight();
      }
      if (percentFirst > 100) {
        percentFirst = 100;
      }
      LargeAdapter adapter = (LargeAdapter) rvPercentage.getAdapter();
      adapter.items().get(i).setPercentage(percentFirst);
      adapter.notifyItemChanged(i);
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
