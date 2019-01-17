
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.ngtnm.ptnsvcssoem2pm;

import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FilenameFilter;

@Service
class ListFiles {

  final static Logger logger
    = LoggerFactory.getLogger(ListFiles.class);

  public ListFiles () {
  }   // public method

  public File[] list () {
    File[] files = null;
    try {
      File dir = new File(path);
      files = dir.listFiles(new FilenameFilter() {
        public boolean accept(File dir, String name) {
          return name.toLowerCase().endsWith(extension);
        }
      });
    } // try
    catch (Exception ex) {
      logger.error(ex.toString());
    } // catch
    finally {
    } // finally
    return files;
  }

  public void setPath (String path) {
    this.path = path;
  }

  public String getPath() {
  	return path;
  }

  @Value("${source_path}")
  private String path;

  @Value("${extension}")
  private String extension;

} // class ListFiles
