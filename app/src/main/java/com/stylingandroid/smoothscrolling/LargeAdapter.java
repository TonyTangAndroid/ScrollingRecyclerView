package com.stylingandroid.smoothscrolling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public final class LargeAdapter extends RecyclerView.Adapter<ItemViewHolder> {

  private final List<String> items;

  private LargeAdapter(List<String> items) {
    this.items = items;
  }

  public static LargeAdapter newInstance(Context context) {
    List<String> items = DataSourceUtil.dataSource(context);
    return new LargeAdapter(items);
  }

  @NonNull
  @Override
  public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return ItemViewHolder.newInstance(view(parent));
  }

  private View view(ViewGroup parent) {
    return LayoutInflater.from(parent.getContext()).inflate(layoutId(), parent, false);
  }

  private int layoutId() {
    return android.R.layout.simple_list_item_1;
  }

  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    String text = items.get(position);
    holder.setText(text);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }
}
