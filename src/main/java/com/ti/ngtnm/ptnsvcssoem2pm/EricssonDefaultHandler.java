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
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.io.File;

class EricssonDefaultHandler extends DefaultHandler {

  final static Logger logger
    = LoggerFactory.getLogger(EricssonDefaultHandler.class);

  static final String EMPTY_STRING = "";
  static final String SOURCE_ID_TOKEN_SEP = "-";

  // tag                                                                            CSV column
  //                                                                                ===========
  final static String XML_NE                                = "NE";
  final static String XML_SOURCE                            = "Source";
  final static String XML_SCHEMEMOAM                        = "SchemeMOAM";
  final static String XML_ENTITY                            = "Entity";
  final static String XML_NENAME                            = "NEName";             // NeAlias*
  final static String XML_NESUFFIX                          = "NESuffix";           // NeAlias*
  final static String XML_NETYPE                            = "NEType";             // NeType
  final static String XML_ENTITYIDENTITY                    = "EntityIdentity";     // EntityIdentity
  final static String XML_DETAILS                           = "Details";
  final static String XML_COMPLIANCE                        = "Compliance";         // Failure
  final static String XML_TIMESTAMP                         = "TimeStamp";
  final static String XML_COUNTER                           = "Counter";

  // attributo
  final static String XML_SOURCE_ID_ATTR                    = "Id";
  final static String XML_SOURCE_TUNNEL_ATTR                = "tunnel";
  final static String XML_SOURCE_LSPINSTANCE_ATTR           = "lspInstance";
  final static String XML_SOURCE_COSBUNDLE_ATTR             = "cosBundle";
  final static String XML_SOURCE_COSBUNDLETX_ATTR           = "cosBundleTx";
  final static String XML_SOURCE_ISLSP_ATTR                 = "isLsp";
  final static String XML_ENTITY_ID_ATTR                    = "Id";
  final static String XML_ENTITY_SOURCEID_ATTR              = "sourceId";
  final static String XML_NE_NEIDONEM_ATTR                  = "NEIdOnEM";           // NeId
  final static String XML_NENAME_LONGNAME_ATTR              = "longName";           // NeAlias*
  final static String XML_TIMESTAMP_LOCALTIMEFORMATID_ATTR  = "localTimeFormatId";  // EndTime
  final static String XML_COUNTER_NAME_ATTR                 = "name";

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

  final static String XML_ENTITY_COMPLIANCE_SUCCESS         = "Success";

  final String counterList[] = {
    EricssonDefaultHandler.XML_COUNTER_NUMOFSAMP_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_DROPEVENTS_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_TOTLOCTETS_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_TOTPACKETS_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_BROADCPACK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_MULTICPACK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_CRCALGNERR_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_UNDSIZPACK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_OVRSIZPACK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_FRAGMENTS_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_JABBERS_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_COLLISIONS_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_UTILIZATIO_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_USF_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_OSF_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_FRAG_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_FFAE_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_OTOK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_UFTOK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_MFTOK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_BFTOK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_OROK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_UFROK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_MFROK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_BFROK_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_TOTTXPKTS_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_TOTRXPKTS_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_TOTTXPKTLO_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_TOTRXPKTLO_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_AVGTXPKTLR_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_AVGRXPKTLR_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_MIN2WDELAY_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_AVG2WDELAY_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_MAX2WDELAY_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_MINRTDELAY_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_AVGRTDELAY_ATTR_VAL,
    EricssonDefaultHandler.XML_COUNTER_MAXRTDELAY_ATTR_VAL
  };

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
      String id = attributes.getValue(EricssonDefaultHandler.XML_ENTITY_ID_ATTR);
      String sourceid = attributes.getValue(EricssonDefaultHandler.XML_ENTITY_SOURCEID_ATTR);
      this.entity = new Entity();
      this.entity.setId(id);
      logger.debug("entity.id: " + this.entity.getId());
      this.entity.setSourceId(sourceid);
      logger.debug("entity.sourceid: " + this.entity.getSourceId());
      String measurepoint = extractMeasurePoint(this.entity.getSourceId());
      this.entity.setMeasurePoint(measurepoint);
      logger.debug("entity.measurepoint: " + this.entity.getMeasurePoint());
      this.bEntity = true;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_DETAILS)) {
      HashMap <String,String> countermap
        = new HashMap <String,String>();
      this.entity.setCounterMap(countermap);
      this.bDetails = true;
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
      this.source.setTunnel(tunnel);
      String lspinstance = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_LSPINSTANCE_ATTR);
      this.source.setLspInstance(lspinstance);
      String islsp = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_ISLSP_ATTR);
      this.source.setIsLsp(islsp);
      String cosbundle = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_COSBUNDLE_ATTR);
      this.source.setCosBundle(cosbundle);
      String cosbundletx = attributes.getValue(EricssonDefaultHandler.XML_SOURCE_COSBUNDLETX_ATTR);
      this.source.setCosBundleTx(cosbundletx);
      this.bSchemeMOAM = true;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_TIMESTAMP)) {
      String timestampfrom
        = attributes.getValue(EricssonDefaultHandler.XML_TIMESTAMP_LOCALTIMEFORMATID_ATTR);
      try {
        // from 2018-12-14 14.00.00 to 14/12/2018 14:00
        DateFormat formatfrom = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        DateFormat formatto = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = formatfrom.parse(timestampfrom);
        String timestampto = formatto.format(date);
        this.entity.setTimeStamp(timestampto);
        logger.debug("EndTime("+timestampfrom+"): " + this.entity.getTimeStamp());
      } // try
      catch (Exception ex) {
        logger.error("error while parsing timestamp '"+timestampfrom+"': "+ex.getLocalizedMessage());
      } // catch
      this.bTimeStamp = true;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_COMPLIANCE)) {
      this.bCompliance = true;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_COUNTER)) {
      this.counterName
        = attributes.getValue(EricssonDefaultHandler.XML_COUNTER_NAME_ATTR);
      this.bCounter = true;
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
      this.sourceMap.put(this.source.getId(), this.source);
      logger.debug("source = '"+this.sourceMap.get(this.source.getId()).toString()+"'");
      this.source = null;
    } // else if
    else if (qName.equalsIgnoreCase(EricssonDefaultHandler.XML_COUNTER)) {
      logger.debug(this.counterName+":" + this.entity.getCounter(this.counterName));
      this.counterName = null;
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
    else if (this.bDetails == true) {
      this.bDetails = false;
    } // else if
    else if (this.bSource == true) {
      this.bSource = false;
    } // else if
    else if (this.bSchemeMOAM == true) {
      this.bSchemeMOAM = false;
    } // else if
    else if (this.bTimeStamp == true) {
      this.bTimeStamp = false;
    } // else if
    else if (this.bCompliance == true) {
      String failure = new String(ch, start, length);
      this.entity.setFailure(failure);
      logger.debug("Compliance(Failure): " + this.entity.getFailure());
      this.bCompliance = false;
    } // else if
    else if (this.bCounter == true) {
      String counterval = new String(ch, start, length);
      this.entity.setCounter(this.counterName, counterval);
      this.bCounter = false;
    } // else if
  }

  private String extractMeasurePoint(String sourceid) {
    String measurepoint = EricssonDefaultHandler.EMPTY_STRING;
    Source source = this.sourceMap.get(sourceid);
    if (source == null) {
      return measurepoint;
    } // if
    else {
      String islsp = source.getIsLsp();
      if (islsp == null) {
        measurepoint
          = extractMeasurePointFromSourceId(sourceid,5,8);
      } // if
      else {
        measurepoint
          = extractMeasurePointFromSourceId(sourceid,7,10);
          measurepoint = measurepoint
            + " "
            + source.getTunnel()
            + "." + source.getLspInstance()
            + "." + source.getCosBundleTx()
            + "." + source.getIsLsp()
            ;
      } // else
    } // else
    return measurepoint;
  }

  private String extractMeasurePointFromSourceId(String sourceid, int first, int last) {
    StringTokenizer tokenizer
      = new StringTokenizer(sourceid, EricssonDefaultHandler.SOURCE_ID_TOKEN_SEP);
    String token;
    String measurepoint = EricssonDefaultHandler.EMPTY_STRING;
    for (int ct=0; tokenizer.hasMoreElements(); ct++) {
      token = tokenizer.nextToken();
      if (ct<first) {
        continue;
      } // if
      else if (ct<last) {
        if (measurepoint.equals(EricssonDefaultHandler.EMPTY_STRING)) {
          measurepoint = token;
        } // if
        else {
          measurepoint = measurepoint + '/' + token;
        } // else
      } // else
    } // for
    return measurepoint;
  }

  public void setCsvFile (File csvfile) {
    this.csvFile = csvfile;
  }

  public File getCsvFile () {
    return this.csvFile;
  }

  private Boolean bNE = false;
  private Boolean bNEName = false;
  private Boolean bNESuffix = false;
  private Boolean bNEType = false;
  private Boolean bEntity = false;
  private Boolean bDetails = false;
  private Boolean bSource = false;
  private Boolean bSchemeMOAM = false;
  private Boolean bTimeStamp = false;
  private Boolean bCompliance = false;
  private Boolean bCounter = false;

  // NE
  private NE ne = null;

  // Source
  private Source source = null;

  // Entity
  private Entity entity = null;

  // List of Source
  private HashMap<String, Source> sourceMap = null;

  // counter name
  private String counterName = null;

  private File csvFile = null;

} // class EricssonDefaultHandler
