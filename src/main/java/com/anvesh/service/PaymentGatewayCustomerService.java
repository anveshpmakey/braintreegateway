package com.anvesh.service;

import com.anvesh.vo.BraintreeCustomerRequest;

/**
 * Created by amakey on 6/28/17.
 */
public interface PaymentGatewayCustomerService {

    public String createCustomer(BraintreeCustomerRequest braintreeCustomerRequest);

    public String createCustomerWithPaymentMethod(BraintreeCustomerRequest braintreeCustomerRequest);

    public String updateCustomerPaymentDetails(BraintreeCustomerRequest braintreeCustomerRequest);

    public String findCustomerById(String customerReference);
}
