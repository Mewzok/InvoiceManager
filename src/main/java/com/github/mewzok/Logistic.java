package com.github.mewzok;

import java.io.Serializable;

public abstract class Logistic implements Serializable {

    protected String companyName;
    protected String address;
    protected String phoneNumber;
    protected String reeferTemperature;
    protected String extraInfo;

    public Logistic() {
    }

    public Logistic(String companyName, String address, String phoneNumber, String reeferTemperature, String extraInfo) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.reeferTemperature = reeferTemperature;
        this.extraInfo = extraInfo;
    }

    // getters
    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getReeferTemperature() {
        return reeferTemperature;
    }

    public String getExtraInfo() { return extraInfo; }

    // setters
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setReeferTemperature(String reeferTemperature) {
        this.reeferTemperature = reeferTemperature;
    }

    public void setExtraInfo(String extraInfo) { this.extraInfo = extraInfo; }
}
