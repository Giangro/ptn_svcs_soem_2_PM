/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.ngtnm.ptnsvcssoem2pm;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Alex
 */
@ComponentScan(basePackages = "com.ti")
@Configuration
public class App {

  final static Logger logger
    = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {

    logger.debug("Initializing spring framework");

    AnnotationConfigApplicationContext ctx
      = new AnnotationConfigApplicationContext(App.class);

    App app = ctx.getBean(App.class);

    logger.info("-- Starting PTN Conversion Tool --");
    // Prints "Hello, World" to the terminal window.
    System.out.println("Hello, World");
  }
}
