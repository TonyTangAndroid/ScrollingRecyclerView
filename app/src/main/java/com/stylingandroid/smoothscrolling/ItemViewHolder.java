package com.stylingandroid.smoothscrolling;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;

public final class ItemViewHolder extends RecyclerView.ViewHolder {

  private final TextView tv_text;
  private final TextView tv_percentage;

  private ItemViewHolder(View itemView) {
    super(itemView);
    this.tv_text = itemView.findViewById(android.R.id.text1);
    this.tv_percentage = itemView.findViewById(R.id.tv_percentage);
  }

  public static ItemViewHolder newInstance(View itemView) {
    return new ItemViewHolder(itemView);
  }

  public void bindView(ItemModel model) {
    tv_text.setText(model.data().text());
    tv_percentage.setText(percentage(model));
  }

  private String percentage(ItemModel model) {
    int position = model.position();
    int percentage = model.data().percentage();
    return String.format(Locale.US, "position %s:%s percentage visible", position, percentage);
  }
}
