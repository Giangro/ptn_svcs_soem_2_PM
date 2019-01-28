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
import org.apache.commons.io.FilenameUtils;
import java.io.FileWriter;

class EricssonConverter {

  final static Logger logger
    = LoggerFactory.getLogger(EricssonConverter.class);

  public EricssonConverter() {
    this.xmlEricssonFile = null;
    this.factory = null;
    this.parser = null;
    this.csvPath = null;
  }    // public method

  public EricssonConverter(File xmlericssonfile, String csvpath) {
    this.xmlEricssonFile = xmlericssonfile;
    this.factory = null;
    this.parser = null;
    this.csvPath = csvpath;
  }    // public method

  public void setXmlEricssonFile (File xmlericssonfile) {
    this.xmlEricssonFile = xmlericssonfile;
  }

  public File getXmlEricssonFile () {
    return this.xmlEricssonFile;
  }

  public void setCsvPath (String csvpath) {
    this.csvPath = csvpath;
  }

  public String getCsvPath () {
    return this.csvPath;
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

      String xmlfilename =
        FilenameUtils.removeExtension(this.xmlEricssonFile.getName());
      logger.debug("xml filename: '"+xmlfilename+"'");
      String csvfilename
        = this.csvPath
        + File.separatorChar
        + FilenameUtils.removeExtension(xmlfilename)
        + ".csv";
      logger.debug("prepare csv file: '"+csvfilename+"'");
      FileWriter csvfilewriter = new FileWriter(csvfilename);
      handler.setCsvFileWriter(csvfilewriter);
      parser.parse(this.xmlEricssonFile, handler);
      csvfilewriter.flush();
      csvfilewriter.close();
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
  private String csvPath;

} // class EricssonConverter
