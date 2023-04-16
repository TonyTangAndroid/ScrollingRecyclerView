package com.stylingandroid.smoothscrolling;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class DataSourceUtil {

  private static final int SIZE = 1000;

  static List<ItemData> dataSource(Context context) {
    List<ItemData> items = new ArrayList<>();
    String format = context.getString(R.string.item_string);
    for (int i = 0; i < SIZE; i++) {
      items.add(new ItemData(String.format(format, i + 1)));
    }
    return items;
  }
}
