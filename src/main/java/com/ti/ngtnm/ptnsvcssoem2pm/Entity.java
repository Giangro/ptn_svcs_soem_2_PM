/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ti.ngtnm.ptnsvcssoem2pm;
import java.util.HashMap;

class Entity {

  public Entity () {
  } // public method

  // counterMap Getter
  public HashMap<String,String> getCounterMap() {
    return this.counterMap;
  }

  // counterMap Setter
  public void setCounterMap(HashMap<String,String> countermap) {
    this.counterMap = countermap;
  }

  // counter Getter
  public String getCounter(String counterkey) {
    return this.counterMap.get(counterkey);
  }

  // counter Setter
  public void setCounter(String counterkey, String countervalue) {
    this.counterMap.put(counterkey,countervalue);
  }

  // measurePoint Getter
  public String getMeasurePoint() {
    return this.measurePoint;
  }

  // measurePoint Setter
  public void setMeasurePoint(String measurepoint) {
    this.measurePoint = measurepoint;
  }

  // failure Getter
  public String getFailure() {
    return this.failure;
  }

  // failure Setter
  public void setFailure(String failure) {
    this.failure = failure;
  }

  // timestamp Getter
  public String getTimeStamp() {
    return this.timeStamp;
  }

  // timestamp Setter
  public void setTimeStamp(String timestamp) {
    this.timeStamp = timestamp;
  }

  // sourceId Getter
  public String getSourceId() {
    return this.sourceId;
  }

  // sourceId Setter
  public void setSourceId(String sourceid) {
    this.sourceId = sourceid;
  }

  // id Getter
  public String getId() {
    return this.id;
  }

  // sourceId Setter
  public void setId(String id) {
    this.id = id;
  }

  // entityIdentity Getter
  public String getEntityIdentity() {
    return this.entityIdentity;
  }

  // EntityIdentity Setter
  public void setEntityIdentity(String entityidentity) {
    this.entityIdentity = entityidentity;
  }

  private String measurePoint = null;
  private String failure = null;
  private String timeStamp = null;
  private String sourceId = null;
  private String id = null;
  private String entityIdentity = null;
  private HashMap <String,String> counterMap = null;

} // class Entity
