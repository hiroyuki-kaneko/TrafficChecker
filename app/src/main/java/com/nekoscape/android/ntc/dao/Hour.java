package com.nekoscape.android.ntc.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table HourTable.
 */
public class Hour {

    private Long id;
    private String timestamp;
    private String years;
    private String months;
    private String days;
    private String hours;
    private String minutes;
    private String seconds;
    private Long msend;
    private Long mrecv;
    private Long osend;
    private Long orecv;

    public Hour() {
    }

    public Hour(Long id) {
        this.id = id;
    }

    public Hour(Long id, String timestamp, String years, String months, String days, String hours, String minutes, String seconds, Long msend, Long mrecv, Long osend, Long orecv) {
        this.id = id;
        this.timestamp = timestamp;
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.msend = msend;
        this.mrecv = mrecv;
        this.osend = osend;
        this.orecv = orecv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public Long getMsend() {
        return msend;
    }

    public void setMsend(Long msend) {
        this.msend = msend;
    }

    public Long getMrecv() {
        return mrecv;
    }

    public void setMrecv(Long mrecv) {
        this.mrecv = mrecv;
    }

    public Long getOsend() {
        return osend;
    }

    public void setOsend(Long osend) {
        this.osend = osend;
    }

    public Long getOrecv() {
        return orecv;
    }

    public void setOrecv(Long orecv) {
        this.orecv = orecv;
    }

}
