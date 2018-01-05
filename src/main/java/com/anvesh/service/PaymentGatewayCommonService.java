package com.anvesh.service;

import com.braintreegateway.BraintreeGateway;

/**
 * Created by amakey on 6/29/17.
 */
public interface PaymentGatewayCommonService {

    BraintreeGateway getPaymentGateway();
}
