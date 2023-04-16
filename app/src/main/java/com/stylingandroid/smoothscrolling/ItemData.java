package com.stylingandroid.smoothscrolling;

public class ItemData {

  private final String text;
  private int percentage;

  public ItemData(String text) {
    this.text = text;
  }

  public String text() {
    return text;
  }

  public int percentage() {
    return percentage;
  }

  public void setPercentage(int percentage) {
    this.percentage = percentage;
  }
}
