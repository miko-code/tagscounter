package com.miki.tags.tagscounter.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class MsgRes {

  @JsonProperty("validTags")
  private Integer validTags;
  public MsgRes(){}


  public MsgRes(Integer validTags) {
    this.validTags = validTags;
  }

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("validTags")
  public Integer getValidTags() {
    return validTags;
  }

  @JsonProperty("validTags")
  public void setValidTags(Integer validTags) {
    this.validTags = validTags;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
