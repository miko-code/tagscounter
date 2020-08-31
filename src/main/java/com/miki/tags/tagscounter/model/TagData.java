package com.miki.tags.tagscounter.model;

public class TagData {
  private String name;
  private int closeTags;
  private int openTags;

  public TagData(String name, int closeTags, int openTags) {
    this.name = name;
    this.closeTags = closeTags;
    this.openTags = openTags;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCloseTags() {
    return closeTags;
  }

  public void setCloseTags(int closeTags) {
    this.closeTags = closeTags;
  }

  public int getOpenTags() {
    return openTags;
  }

  public void setOpenTags(int openTags) {
    this.openTags = openTags;
  }


}
