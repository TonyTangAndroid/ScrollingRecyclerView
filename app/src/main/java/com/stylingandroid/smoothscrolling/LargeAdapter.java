package com.stylingandroid.smoothscrolling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import timber.log.Timber;

public final class LargeAdapter extends RecyclerView.Adapter<ItemViewHolder> {

  private final List<ItemData> items;

  private LargeAdapter(List<ItemData> items) {
    this.items = items;
  }

  public static LargeAdapter newInstance(Context context) {
    List<ItemData> items = DataSourceUtil.dataSource(context);
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
    return R.layout.item_text_large;
  }

  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    ItemModel model = model(position);
    Timber.i("[tracing]:onBindViewHolder:%s:%s", model.position(), model.data());
    holder.bindView(model);
  }

  private ItemModel model(int position) {
    ItemData item = items.get(position);
    return ItemModel.create(position, item);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public List<ItemData> items() {
    return items;
  }
}
