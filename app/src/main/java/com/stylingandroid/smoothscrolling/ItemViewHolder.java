package com.stylingandroid.smoothscrolling;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public final class ItemViewHolder extends RecyclerView.ViewHolder {

  private final TextView textView;

  private ItemViewHolder(View itemView, TextView textView) {
    super(itemView);
    this.textView = textView;
  }

  public static ItemViewHolder newInstance(View itemView) {
    TextView textView = itemView.findViewById(android.R.id.text1);
    return new ItemViewHolder(itemView, textView);
  }

  public void bindView(CharSequence text) {

    textView.setText(text);
  }
}
