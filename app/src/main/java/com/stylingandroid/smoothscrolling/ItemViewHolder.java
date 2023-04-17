package com.stylingandroid.smoothscrolling;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;

public final class ItemViewHolder extends RecyclerView.ViewHolder {

  private final TextView tv_title;
  private final TextView tv_content;
  private final TextView tv_percentage;

  private ItemViewHolder(View itemView) {
    super(itemView);
    this.tv_title = itemView.findViewById(R.id.tv_title);
    this.tv_content = itemView.findViewById(R.id.tv_content);
    this.tv_percentage = itemView.findViewById(R.id.tv_percentage);
  }

  public static ItemViewHolder newInstance(View itemView) {
    return new ItemViewHolder(itemView);
  }

  public void bindView(ItemModel model) {
    int percentage = model.data().percentage();
    int position = model.position();
    String text = model.data().text();
    tv_title.setText(format("percentage:%s,text:%s", percentage, text));
    tv_percentage.setText(format("position:%s,percentage:%s", position, percentage));
    tv_content.setText(format("percentage:%s,position:%s", percentage, position));
  }

  private String format(String template, Object... args) {
    return String.format(Locale.US, template, args);
  }
}
