package com.github.mewzok;

import java.io.Serializable;

public class Invoice implements Serializable {
    private String rkNumber;
    private String otbNumber;
    private Broker broker;
    private Shipper shipper;
    private Receiver receiver;
    private String gross;
    private String pickupDate;
    private String deliveryDate;
    private String factorCostPercent;
    private String factorCostPay;
    private String factorDate;
    private String factorDueDate;
    private String dispatchCostPercent;
    private String dispatchPay;
    private String otbCost;
    private String net;
    private boolean validInvoice;

    public Invoice() {
        this.rkNumber = "";
        this.otbNumber = "";
        this.broker = null;
        this.shipper = null;
        this.receiver = null;
        this.gross = "0";
        this.pickupDate = "";
        this.deliveryDate = "";
        this.factorCostPercent = "0";
        this.factorCostPay = "0";
        this.factorDate = "";
        this.factorDueDate = "";
        this.dispatchCostPercent = "0";
        this.dispatchPay = "0";
        this.otbCost = "0";
        this.net = "0";
        this.validInvoice = false;
    };

    // all arg constructor
    public Invoice(String rkNumber, String otbNumber, Broker broker, Shipper shipper, Receiver receiver,
            String gross, String pickupDate, String deliveryDate, String factorCostPercent, String factorCostPay,
                   String factorDate, String factorDueDate, String dispatchCostPercent, String dispatchPay,
            String otbCost, String net) {
        this.rkNumber = rkNumber;
        this.otbNumber = otbNumber;
        this.broker = broker;
        this.shipper = shipper;
        this.receiver = receiver;
        this.gross = gross;
        this.pickupDate = pickupDate;
        this.deliveryDate = deliveryDate;
        this.factorCostPercent = factorCostPercent;
        this.factorCostPay = factorCostPay;
        this.factorDate = factorDate;
        this.factorDueDate = factorDueDate;
        this.dispatchCostPercent = dispatchCostPercent;
        this.dispatchPay = dispatchPay;
        this.otbCost = otbCost;
        this.net = net;
        this.validInvoice = true;
    }

    // Getters
    public String getRkNumber() {
        return rkNumber;
    }

    public String getOtbNumber() {
        return otbNumber;
    }

    public Broker getBroker() {
        return broker;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public String getGross() {
        return gross;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getFactorCostPercent() {
        return factorCostPercent;
    }

    public String getFactorCostPay() {
        return factorCostPay;
    }

    public String getFactorDate() {
        return factorDate;
    }

    public String getFactorDueDate() {
        return factorDueDate;
    }

    public String getDispatchCostPercent() {
        return dispatchCostPercent;
    }

    public String getDispatchPay() { return dispatchPay; }

    public String getOtbCost() {
        return otbCost;
    }

    public String getNet() {
        return net;
    }

    public boolean isValidInvoice() {
        return validInvoice;
    }

    // Setters
    public void setRkNumber(String rkNumber) {
        this.rkNumber = rkNumber;
    }

    public void setOtbNumber(String otbNumber) {
        this.otbNumber = otbNumber;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setFactorCostPercent(String factorCostPercent) {
        this.factorCostPercent = factorCostPercent;
    }

    public void setFactorCostPay(String factorCostPay) {
        this.factorCostPay = factorCostPay;
    }

    public void setFactorDate(String factorDate) {
        this.factorDate = factorDate;
    }

    public void setFactorDueDate(String factorDueDate) {
        this.factorDueDate = factorDueDate;
    }

    public void setDispatchCostPercent(String dispatchCost) {
        //this.dispatchCostPercent = dispatchCostPercent;
    }

    public void setDispatchPay(String dispatchPay) {this.dispatchPay = dispatchPay; }

    public void setOtbCost(String otbCost) {
        this.otbCost = otbCost;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public void setValidInvoice(boolean validInvoice) {
        this.validInvoice = validInvoice;
    }
}
