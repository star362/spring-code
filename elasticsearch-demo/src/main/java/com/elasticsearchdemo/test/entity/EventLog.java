package com.elasticsearchdemo.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-07-01 10:49
 *
 * <p>
 */
@Document(indexName = "t_event_log", type = "t_event_log")
public class EventLog implements Serializable {

    private static final long serialVersionUID = -4656232387451205922L;

    //    @JsonAlias("AST_ID")
    @JsonProperty("AST_ID")
    private String astId;
    @JsonProperty("AST_TYPE")
    private String astType;
    @JsonProperty("DIP")
    private String dip;
    @JsonProperty("DMAC")
    private String dmac;
    @JsonProperty("DPORT")
    private String dport;
    @JsonProperty("EVENT_ITEM")
    private String eventItem;
    @JsonProperty("EVENT_LEVEL")
    private String eventLevel;
    @JsonProperty("EVENT_TYPE")
    private String eventType;
    @JsonProperty("FACTORY_ID")
    private String factoryId;
    @Id
    @JsonProperty("ID")
    private String id;
    @JsonProperty("LOG_ID")
    private String logId;
    @JsonProperty("LOG_TYPE")
    private String logType;

    //    @Field(type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OCCURRENCE_TIME")
    private Date occurrenceTime;
    @JsonProperty("PROTOCOL_TYPE")
    private String protocolType;
    @JsonProperty("RULE_ID")
    private String ruleId;
    @JsonProperty("RULE_TYPE")
    private String ruleType;
    @JsonProperty("SIP")
    private String sip;
    @JsonProperty("SMAC")
    private String smac;
    @JsonProperty("SPORT")
    private String sport;
    @JsonProperty("SYSTEM_ID")
    private String systemId;
    @JsonProperty("SYSTEM_TYPE")
    private String systemType;
    @JsonProperty("WORKSHOP_ID")
    private String workshopId;

    public String getAstId() {
        return astId;
    }

    public void setAstId(String astId) {
        this.astId = astId;
    }

    public String getAstType() {
        return astType;
    }

    public void setAstType(String astType) {
        this.astType = astType;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public String getDmac() {
        return dmac;
    }

    public void setDmac(String dmac) {
        this.dmac = dmac;
    }

    public String getDport() {
        return dport;
    }

    public void setDport(String dport) {
        this.dport = dport;
    }

    public String getEventItem() {
        return eventItem;
    }

    public void setEventItem(String eventItem) {
        this.eventItem = eventItem;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getSmac() {
        return smac;
    }

    public void setSmac(String smac) {
        this.smac = smac;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }


    @Override
    public String toString() {
        return "EventLog{" +
                "astId='" + astId + '\'' +
                ", astType='" + astType + '\'' +
                ", dip='" + dip + '\'' +
                ", dmac='" + dmac + '\'' +
                ", dport='" + dport + '\'' +
                ", eventItem='" + eventItem + '\'' +
                ", eventLevel='" + eventLevel + '\'' +
                ", eventType='" + eventType + '\'' +
                ", factoryId='" + factoryId + '\'' +
                ", id='" + id + '\'' +
                ", logId='" + logId + '\'' +
                ", logType='" + logType + '\'' +
                ", occurrenceTime=" + occurrenceTime +
                ", protocolType='" + protocolType + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", ruleType='" + ruleType + '\'' +
                ", sip='" + sip + '\'' +
                ", smac='" + smac + '\'' +
                ", sport='" + sport + '\'' +
                ", systemId='" + systemId + '\'' +
                ", systemType='" + systemType + '\'' +
                ", workshopId='" + workshopId + '\'' +
                '}';
    }
}
