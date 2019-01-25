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
import java.util.HashMap;

class EricssonDefaultHandler extends DefaultHandler {

  final static Logger logger
    = LoggerFactory.getLogger(EricssonDefaultHandler.class);

  // tag                                                                               CSV column
  //                                                                                   ===========
  final static String XML_NE                                = "NE";
  final static String XML_SOURCE                            = "Source";
  final static String XML_SCHEMEMOAM                        = "SchemeMOAM";
  final static String XML_ENTITY                            = "Entity";
  final static String XML_NENAME                            = "NEName";             // NeAlias*
  final static String XML_NESUFFIX                          = "NESuffix";           // NeAlias*
  final static String XML_NETYPE                            = "NEType";             // NeType
  final static String XML_ENTITYIDENTITY                    = "EntityIdentity";     // EntityIdentity
  final static String XML_TIMESTAMP_LOCALTIMEFORMATID_ATTR  = "localTimeFormatId";  // EndTime
  final static String XML_COMPLIANCE                        = "Compliance";         // Failure

  // attributo
  final static String XML_SOURCE_ID_ATTR                    = "Id";
  final static String XML_SOURCE_TUNNEL_ATTR                = "Tunnel";
  final static String XML_SOURCE_LSPINSTANCE_ATTR           = "lspInstance";
  final static String XML_SOURCE_COSBUNDLE_ATTR             = "cosBundle";
  final static String XML_SOURCE_ISLSP_ATTR                 = "isLsp";
  final static String XML_ENTITY_ID_ATTR                    = "Id";
  final static String XML_NE_NEIDONEM_ATTR                  = "NEIdOnEM";           // NeId
  final static String XML_NENAME_LONGNAME_ATTR              = "longName";           // NeAlias*

  // valore attributo
  final static String XML_COUNTER_NUMOFSAMP_ATTR_VAL        = "NumOfSamp";          // NumOfSamp
  final static String XML_COUNTER_DROPEVENTS_ATTR_VAL       = "DropEvents";         // DropEvents
  final static String XML_COUNTER_TOTLOCTETS_ATTR_VAL       = "TotlOctets";         // TotlOctets
  final static String XML_COUNTER_TOTPACKETS_ATTR_VAL       = "TotPackets";         // TotPackets
  final static String XML_COUNTER_BROADCPACK_ATTR_VAL       = "BroadcPack";         // BroadcPack
  final static String XML_COUNTER_MULTICPACK_ATTR_VAL       = "MulticPack";         // MulticPack
  final static String XML_COUNTER_CRCALGNERR_ATTR_VAL       = "CrcAlgnErr";         // CrcAlgnErr
  final static String XML_COUNTER_UNDSIZPACK_ATTR_VAL       = "UndSizPack";         // UndSizPack
  final static String XML_COUNTER_OVRSIZPACK_ATTR_VAL       = "OvrSizPack";         // OvrSizPack
  final static String XML_COUNTER_FRAGMENTS_ATTR_VAL        = "Fragments";          // Fragments
  final static String XML_COUNTER_JABBERS_ATTR_VAL          = "Jabbers";            // Jabbers
  final static String XML_COUNTER_COLLISIONS_ATTR_VAL       = "Collisions";         // Collisions
  final static String XML_COUNTER_UTILIZATIO_ATTR_VAL       = "Utilizatio";         // Utilizatio
  final static String XML_COUNTER_USF_ATTR_VAL              = "USF";                // USF
  final static String XML_COUNTER_OSF_ATTR_VAL              = "OSF";                // OSF
  final static String XML_COUNTER_FRAG_ATTR_VAL             = "Frag";               // Frag
  final static String XML_COUNTER_FFAE_ATTR_VAL             = "FFAE";               // FFAE
  final static String XML_COUNTER_OTOK_ATTR_VAL             = "OTOk";               // OTOk
  final static String XML_COUNTER_UFTOK_ATTR_VAL            = "UFTOk";              // UFTOk
  final static String XML_COUNTER_MFTOK_ATTR_VAL            = "MFTOk";              // MFTOk
  final static String XML_COUNTER_BFTOK_ATTR_VAL            = "BFTOk";              // BFTOk
  final static String XML_COUNTER_OROK_ATTR_VAL             = "OROk";               // OROk
  final static String XML_COUNTER_UFROK_ATTR_VAL            = "UFROk";              // UFROk
  final static String XML_COUNTER_MFROK_ATTR_VAL            = "MFROk";              // MFROk
  final static String XML_COUNTER_BFROK_ATTR_VAL            = "BFROk";              // BFROk
  final static String XML_COUNTER_TOTTXPKTS_ATTR_VAL        = "totTxPkts";          // totTxPkts
  final static String XML_COUNTER_TOTRXPKTS_ATTR_VAL        = "totRxPkts";          // totRxPkts
  final static String XML_COUNTER_TOTTXPKTLO_ATTR_VAL       = "totTxPktLo";         // totTxPktLo
  final static String XML_COUNTER_TOTRXPKTLO_ATTR_VAL       = "totRxPktLo";         // totRxPktLo
  final static String XML_COUNTER_AVGTXPKTLR_ATTR_VAL       = "avgTxPktLR";         // avgTxPktLR
  final static String XML_COUNTER_AVGRXPKTLR_ATTR_VAL       = "avgRxPktLR";         // avgRxPktLR
  final static String XML_COUNTER_MIN2WDELAY_ATTR_VAL       = "min2WDelay";         // min2WDelay
  final static String XML_COUNTER_AVG2WDELAY_ATTR_VAL       = "avg2WDelay";         // avg2WDelay
  final static String XML_COUNTER_MAX2WDELAY_ATTR_VAL       = "max2WDelay";         // max2WDelay
  final static String XML_COUNTER_MINRTDELAY_ATTR_VAL       = "minRTDelay";         // minRTDelay
  final static String XML_COUNTER_AVGRTDELAY_ATTR_VAL       = "avgRTDelay";         // avgRTDelay
  final static String XML_COUNTER_MAXRTDELAY_ATTR_VAL       = "maxRTDelay";         // maxRTDelay

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
  throws SAXException {
    if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_NE)) {
      String neidonemstr = attributes.getValue(EricssonDefaultHandler.XML_NE_NEIDONEM_ATTR);
      this.ne = new NE();
      this.ne.setNEIdOnEM(neidonemstr);
      logger.debug("NEIdOnEM(NeId): " + this.ne.getNEIdOnEM());
      this.bNE = true;
    } // if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_NENAME)) {
      String longname = attributes.getValue(EricssonDefaultHandler.XML_NENAME_LONGNAME_ATTR);
      this.ne.setNEName(longname);
      logger.debug("longName(part of NeAlias*): " + this.ne.getNEName());
      this.bNEName = true;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_NESUFFIX)) {
      this.bNESuffix = true;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_NETYPE)) {
      this.bNEType = true;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_ENTITY)) {
      this.bEntity = true;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_SOURCE)) {
      String id = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_ID_ATTR);
      if (this.sourceMap == null) {
        this.sourceMap = new HashMap<String, Source>();
      } // if
      source = new Source();
      source.setId(id);
      this.sourceMap.put(id,source);
      this.bSource = true;
      logger.debug("create new source with id: '"+this.sourceMap.get(id).getId()+"'");
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_SCHEMEMOAM)) {      
      String tunnel = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_TUNNEL_ATTR);
      String lspinstance = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_LSPINSTANCE_ATTR);
      String islsp = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_ISLSP_ATTR);
      String cosbundle = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_COSBUNDLE_ATTR);
      this.bSchemeMOAM = true;
    } // else if
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_NE)) {
      this.ne.setNEAlias(
        this.ne.getNEName()+"."+this.ne.getNESuffix()
      );
      logger.debug("NEAlias: " + this.ne.getNEAlias());
      this.ne = null;
    } // if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_SOURCE)) {
      this.source = null;
    } // else if

  }

  @Override
  public void characters(char ch[], int start, int length) throws SAXException {
    if (this.bNE == true) {
      this.bNE = false;
    } // if
    else if (this.bNEName == true) {
      this.bNEName = false;
    } // else if
    else if (this.bNESuffix == true) {
      String nesuffix = new String(ch, start, length);
      this.ne.setNESuffix(nesuffix);
      logger.debug("NESuffix(part of NeAlias*): " + this.ne.getNESuffix());
      this.bNESuffix = false;
    } // else if
    else if (this.bNEType == true) {
      String netype = new String(ch, start, length);
      this.ne.setNEType(netype);
      logger.debug("NEType(NeType): " + this.ne.getNEType());
      this.bNEType = false;
    } // else if
    else if (this.bEntity == true) {
      this.bEntity = false;
    } // else if
    else if (this.bSource == true) {
      this.bSource = false;
    } // else if
    else if (this.bSchemeMOAM == true) {
      this.bSchemeMOAM = false;
    } // else if
  }

  private Boolean bNE = false;
  private Boolean bNEName = false;
  private Boolean bNESuffix = false;
  private Boolean bNEType = false;
  private Boolean bEntity = false;
  private Boolean bSource = false;
  private Boolean bSchemeMOAM = false;

  // NE
  private NE ne = null;

  // Source
  private Source source;

  // List of Source
  private HashMap<String, Source> sourceMap = null;

} // class EricssonDefaultHandler
