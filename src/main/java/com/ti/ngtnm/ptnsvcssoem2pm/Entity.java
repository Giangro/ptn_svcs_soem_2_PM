/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ti.ngtnm.ptnsvcssoem2pm;
import java.util.HashMap;

class Entity {

  public Entity () {
    counters = new HashMap<String, String>();
  } // public method

  // counter Getter
  public String getCounter(String counterkey) {
    return this.counters.get(counterkey);
  }

  // counter Setter
  public void setCounter(String counterkey, String countervalue) {
    this.counters.put(counterkey,countervalue);
  }

  // failure Getter
  public String getFailure() {
    return this.failure;
  }

  // failure Setter
  public void setFailure(String failure) {
    this.failure = failure;
  }

  private HashMap <String,String> counters = null;
  private String failure;

} // class Entity
