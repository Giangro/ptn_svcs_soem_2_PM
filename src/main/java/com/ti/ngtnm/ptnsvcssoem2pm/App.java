/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.ngtnm.ptnsvcssoem2pm;

import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;


/**
 *
 * @author Alex
 */
@ComponentScan(basePackages = "com.ti")
@Configuration
@PropertySource("classpath:ptn_svcs_soem_2_PM.properties")
public class App {

  final static Logger logger
    = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {

    logger.debug("Initializing spring framework");
    AnnotationConfigApplicationContext ctx = null;
    try {
      ctx = new AnnotationConfigApplicationContext(App.class);
      App app = ctx.getBean(App.class);
      app.doStart();
    } // try
    finally {
      if (ctx!=null) {
        ctx.close();
        logger.info("exit");
      } // if
    } // finally

  }

  private void doStart() {
    logger.info("Starting PTN Conversion Tool version "+this.version);
    logger.debug("listing files in directory '"+lsfFiles.getPath()+"'");
    try {
      File[] files
        = lsfFiles.list();
      if (files!=null) {
        logger.debug("prepare for converting files");
        for (int i = 0; i < files.length; i++) {
          if (files[i].isFile()) {
            logger.debug("File '" + files[i].getName() + "'");
          } else if (files[i].isDirectory()) {
            logger.debug("Directory " + files[i].getName());
          }
        } // for
        cvtFiles.convert(files);
      } // if
    } // try
    catch (Exception ex) {
      logger.error(ex.getLocalizedMessage());
    } // catch
  }

  @Value("${version}")
  private String version;

  @Autowired
  private ListFiles lsfFiles;

  @Autowired
  private ConvertFiles cvtFiles;

} // class App
