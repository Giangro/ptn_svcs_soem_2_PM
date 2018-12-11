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

/**
 *
 * @author Alex
 */
@ComponentScan(basePackages = "com.ti")
@Configuration
@PropertySource("classpath:conf/ptn_svcs_soem_2_PM.properties")
public class App {

  final static Logger logger
    = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {

    logger.debug("Initializing spring framework");
    AnnotationConfigApplicationContext ctx
      = new AnnotationConfigApplicationContext(App.class);
    App app = ctx.getBean(App.class);
    logger.info("-- Starting PTN Conversion Tool --");
    app.doStart();

  }

  private void doStart() {
    logger.info("version: "+this.version);
    // do stuff
  }

  @Value("${version}")
  private String version;

}
