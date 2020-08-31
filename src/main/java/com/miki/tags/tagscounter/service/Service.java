package com.miki.tags.tagscounter.service;

import com.miki.tags.tagscounter.model.TagData;

import java.util.*;

public class Service {
/*
calNumberOfValidTags receive input string with html tag
we need to figure out how many closing an opening tags we have.
the assumption is that we cant have more closing tags then opening.
so we iterate over each char and map the tags to tag name and creating map of tags objects
and summing the valid tags.
 */
  public int calNumberOfValidTags(String input) {
    HashMap<String, TagData> tagDataHashMap = getStringTagDataHashMap(input);
    return calTags(tagDataHashMap);
  }
//Generate map of tags name and open close tags for each tag.
  private HashMap<String, TagData> getStringTagDataHashMap(String input) {
    HashMap<String, TagData> tagDataHashMap = new HashMap<>();
    String tagName = "";
    int startTag = 0;
    int endTag = 0;
    for (int i = 0; i < input.length(); i++) {
      startTag = input.indexOf('<', i);
      endTag = input.indexOf('>', i);
      tagName = input.substring(startTag + 1, endTag);

      //is it closing tag
      if (tagName.charAt(0) == '/') {
        mapTags(tagDataHashMap, new TagData(tagName.substring(1), 1, 0));
      } else {
        mapTags(tagDataHashMap, new TagData(tagName, 0, 1));
      }
      //move the loop to start from the next tags position.
      i = endTag;
    }
    return tagDataHashMap;
  }
//Create or update tag name  and tags value.
  private void mapTags(HashMap<String, TagData> tagDataHashMap, TagData tagDataMap) {
    if (tagDataHashMap.containsKey(tagDataMap.getName())) {
      TagData tagDataVal = tagDataHashMap.get(tagDataMap.getName());
      tagDataVal.setCloseTags(tagDataVal.getCloseTags() + tagDataMap.getCloseTags());
      tagDataVal.setOpenTags(tagDataVal.getOpenTags() + tagDataMap.getOpenTags());
      tagDataHashMap.put(tagDataVal.getName(), tagDataVal);
    } else tagDataHashMap.put(tagDataMap.getName(), tagDataMap);
  }
//Calculate valid tags.
  private int calTags(HashMap<String, TagData> tagDataHashMap) {
    int sum = 0;
    Iterator<Map.Entry<String, TagData>> iterator = tagDataHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, TagData> entry = iterator.next();
      if (entry.getValue().getOpenTags() >= entry.getValue().getCloseTags()) {
        sum += entry.getValue().getCloseTags();
      }
    }
    return sum;
  }
}
