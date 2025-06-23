package com.github.mewzok;

public class Transport extends Logistic {
    protected String deliveryAddress;
    protected String pickupDateTime;
    protected String approximateWeight;

    public Transport() {
    }

    public Transport(String companyName, String address, String phoneNumber, String reeferTemperature, String deliveryAddress,
                     String pickupDateTime, String approximateWeight, String extraInfo) {
        super(companyName, address, phoneNumber, reeferTemperature, extraInfo);
        this.deliveryAddress = deliveryAddress;
        this.pickupDateTime = pickupDateTime;
        this.approximateWeight = approximateWeight;
    }

    // getters
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public String getApproximateWeight() {
        return approximateWeight;
    }

    // setters
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public void setApproximateWeight(String approximateWeight) {
        this.approximateWeight = approximateWeight;
    }
}
