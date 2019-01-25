/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.ngtnm.ptnsvcssoem2pm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.File;


@Service
class ConvertFiles {

  final static Logger logger
    = LoggerFactory.getLogger(ConvertFiles.class);

  final static String PROCESSING_EXT = ".processing";
  final static String OK_EXT = ".ok";
  final static String NOK_EXT = ".nok";

  public ConvertFiles() {
    this.ericssonConverter
      = new EricssonConverter();
  }    // public method

  public void convert(File[] files) {
    try {
      for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
          logger.info("Converting file '" + files[i].getName() + "'");
          logger.debug("start phase 1");
          String abspathfilename = files[i].getAbsolutePath();
          File toconvert = new File (abspathfilename + ConvertFiles.PROCESSING_EXT);
          logger.debug("renaming file to '"+abspathfilename + ConvertFiles.PROCESSING_EXT + "'");
          if (files[i].renameTo(toconvert) == true) {
            ericssonConverter.setXmlEricssonFile(toconvert);            
            if (ericssonConverter.convertFile() == true) {
              logger.info ("file '" + files[i].getName() + "' successfully converted");
              File converted = new File (abspathfilename + ConvertFiles.OK_EXT);
              toconvert.renameTo(converted);
            } // if
            else {
              logger.error ("file '" + files[i].getName() + "' error in conversion");
              File notconverted = new File (abspathfilename + ConvertFiles.NOK_EXT);
              toconvert.renameTo(notconverted);
            } //else
          } // if
          else {
              logger.warn("** file '" + files[i].getName() + "' can't be renamed and was ignored");
              logger.warn("** check for permissions and/or target file already exists");
          } // else
        }   // if
      }   // for
    }   // try
    catch (Exception ex) {
      logger.error(ex.getLocalizedMessage());
    }   // catch
  }

  private EricssonConverter ericssonConverter;

} // class ConvertFiles
