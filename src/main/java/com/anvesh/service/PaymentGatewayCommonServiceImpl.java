package com.anvesh.service;

import com.anvesh.gateway.BraintreePaymentGateway;
import com.braintreegateway.BraintreeGateway;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by amakey on 6/29/17.
 */
public class PaymentGatewayCommonServiceImpl implements PaymentGatewayCommonService {

    @Autowired
    BraintreePaymentGateway braintreePaymentGateway;

    @Override
    public BraintreeGateway getPaymentGateway() {
        return braintreePaymentGateway.connectToBraintreeGateway();
    }
}
