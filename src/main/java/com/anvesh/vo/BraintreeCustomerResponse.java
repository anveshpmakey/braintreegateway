package com.anvesh.vo;

/**
 * Created by anvesh.makey on 13/12/17.
 */
public class BraintreeCustomerResponse {

    private String customerId;
    private String paymentToken;
    private boolean isDefaultPaymentMethod;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public boolean isDefaultPaymentMethod() {
        return isDefaultPaymentMethod;
    }

    public void setDefaultPaymentMethod(boolean defaultPaymentMethod) {
        isDefaultPaymentMethod = defaultPaymentMethod;
    }
}
