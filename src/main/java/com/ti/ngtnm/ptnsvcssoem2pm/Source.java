/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ti.ngtnm.ptnsvcssoem2pm;

class Source {

  public Source () {
  } // public method

  // id Getter
  public String getId() {
    return this.id;
  }

  // id Setter
  public void setId (String id) {
    this.id = id;
  }

  // tunnel Getter
  public String getTunnel() {
    return this.tunnel;
  }

  // tunnel Setter
  public void setTunnel (String tunnel) {
    this.tunnel = tunnel;
  }

  // lspInstance Getter
  public String getLspInstance() {
    return this.lspInstance;
  }

  // lspInstance Setter
  public void setLspInstance (String lspinstance) {
    this.lspInstance = lspinstance;
  }

  // cosBundle Getter
  public String getCosBundle() {
    return this.cosBundle;
  }

  // cosBundle Setter
  public void setCosBundle (String cosbundle) {
    this.cosBundle = cosbundle;
  }

  // isLsp Getter
  public String getIsLsp() {
    return this.isLsp;
  }

  // isLsp Setter
  public void setIsLsp (String islsp) {
    this.isLsp = islsp;
  }

  @Override
  public String toString() {
    return "Source{" + "id=" + id + ", tunnel=" + tunnel + ", lspInstance=" + lspInstance + ", cosBundle=" + cosBundle + ", isLsp=" + isLsp + '}';
  }

  private String id = null;
  private String tunnel = null;
  private String lspInstance = null;
  private String cosBundle = null;
  private String isLsp = null;

} // class Source
