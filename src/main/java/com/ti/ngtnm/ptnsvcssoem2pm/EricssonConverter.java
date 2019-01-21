/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.ngtnm.ptnsvcssoem2pm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class EricssonConverter {

  final static Logger logger
    = LoggerFactory.getLogger(EricssonConverter.class);

  public EricssonConverter() {
    this.xmlEricssonFile = null;
    this.factory = null;
    this.parser = null;
  }    // public method

  public EricssonConverter(String xmlericssonfile) {
    this.xmlEricssonFile = xmlericssonfile;
    this.factory = null;
    this.parser = null;
  }    // public method

  public void setXmlEricssonFile (String xmlericssonfile) {
    this.xmlEricssonFile = xmlericssonfile;
  }

  public void convertFile() {
    if (this.xmlEricssonFile==null) {
      logger.warn ("no file name provided");
      return;
    } // if

    if (parser == null && this.createParser() == false) {
      logger.error ("XML parser cannot be created. Exiting.");
      return;
    } // if

    // do stuff

  }

  public String getXmlEricssonFile () {
      return this.xmlEricssonFile;
  }

  private Boolean createParser() {

    try {
      this.factory = SAXParserFactory.newInstance();
      this.parser = factory.newSAXParser();
      return true;
    } // try
    catch (Exception ex) {
      logger.error(ex.getLocalizedMessage());
      return false;
    } // catch
  }

  private String xmlEricssonFile;
  private SAXParserFactory factory;
  private SAXParser parser;

} // class
