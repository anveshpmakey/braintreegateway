package com.anvesh.service;

import com.anvesh.gateway.BraintreePaymentGateway;
import com.braintreegateway.BraintreeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amakey on 6/29/17.
 */
@Service
public class PaymentGatewayClientServiceImpl implements PaymentGatewayClientService {

    @Autowired
    BraintreePaymentGateway braintreePaymentGateway;

    BraintreeGateway braintreeGateway;

    @Override
    public String generateToken() {
        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();
        String brainTreeToken = braintreeGateway.clientToken().generate();

        return brainTreeToken;
    }
}
