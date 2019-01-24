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
import java.io.File;
import java.io.IOException;

class EricssonConverter {

  final static Logger logger
    = LoggerFactory.getLogger(EricssonConverter.class);

  public EricssonConverter() {
    this.xmlEricssonFile = null;
    this.factory = null;
    this.parser = null;
  }    // public method

  public EricssonConverter(File xmlericssonfile) {
    this.xmlEricssonFile = xmlericssonfile;
    this.factory = null;
    this.parser = null;
  }    // public method

  public void setXmlEricssonFile (File xmlericssonfile) {
    this.xmlEricssonFile = xmlericssonfile;
  }

  public Boolean convertFile() {
    if (this.xmlEricssonFile==null) {
      logger.warn("no file to parse was provided");
      return false;
    } // if

    if (parser == null && this.createParser() == false) {
      logger.error("XML parser cannot be created. Exiting.");
      return false;
    } // if

    try {
      EricssonDefaultHandler handler
        = new EricssonDefaultHandler();
      parser.parse(this.xmlEricssonFile, handler);
      return true;
    } // try
    catch (SAXException ex) {
      logger.error("parse error: "+ex.getLocalizedMessage());
      return false;
    } // catch
    catch (IOException ex) {
      logger.error("IO Exception: "+ex.getLocalizedMessage());
      return false;
    } //

  }

  public File getXmlEricssonFile () {
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

  private File xmlEricssonFile;
  private SAXParserFactory factory;
  private SAXParser parser;

} // class EricssonConverter
