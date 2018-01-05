package com.anvesh.service;

import com.anvesh.gateway.BraintreePaymentGateway;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCardRequest;
import com.braintreegateway.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amakey on 6/29/17.
 */
@Service
public class PaymentGatewayCreditCardServiceImpl implements PaymentGatewayCreditCardService {

    @Autowired
    BraintreePaymentGateway braintreePaymentGateway;

    BraintreeGateway braintreeGateway;

    @Override
    public Result saveCreditCard() {

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();

        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.customerId("875131869");
        creditCardRequest.cardholderName("Anvesh Makey");
        creditCardRequest.number("4111111111111111");
        creditCardRequest.expirationMonth("03");
        creditCardRequest.expirationYear("2021");
        creditCardRequest.cvv("123");

        Result result = braintreeGateway.creditCard().create(creditCardRequest);

        return result;

    }
}
