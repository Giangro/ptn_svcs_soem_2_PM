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
          files[i].renameTo(toconvert);

        }   // if
      }   // for
    }   // try
    catch (Exception ex) {
      logger.error(ex.getLocalizedMessage());
    }   // catch
  }

} // class ConvertFiles
