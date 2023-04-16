package com.stylingandroid.smoothscrolling;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class MainActivity extends Activity {

  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Timber.i("[tracing]:MainActivity onCreate:%s", this);

    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.recyclerview);
    recyclerView.setAdapter(LargeAdapter.newInstance(this));
    int duration = getResources().getInteger(R.integer.scroll_duration);
    recyclerView.setLayoutManager(
        new ScrollingLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, duration));
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
