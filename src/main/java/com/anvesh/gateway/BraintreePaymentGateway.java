package com.anvesh.gateway;

import com.anvesh.configuration.PaymentGatewayConfiguration;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by amakey on 6/5/17.
 */
@Component
public class BraintreePaymentGateway {

    @Autowired
    PaymentGatewayConfiguration paymentGatewayConfiguration;

    /*BraintreeGateway braintreeGateway = new BraintreeGateway(Environment.SANDBOX, PaymentGatewayConstants.BRAINTREE_MERCHANT_ID,
            PaymentGatewayConstants.BRAITREE_PUBLIC_KEY, PaymentGatewayConstants.BRAINTREE_PRIVATE_KEY);*/

    public BraintreeGateway connectToBraintreeGateway() {

        /*BraintreeGateway braintreeGateway = new BraintreeGateway(Environment.SANDBOX, "ndrbjrcv4cbf5b89",
                "2fxt8zk4r8npqnmh", "66b02ae8cf19c5b781cd39ce7403f225");*/

        BraintreeGateway braintreeGateway = new BraintreeGateway(Environment.parseEnvironment(paymentGatewayConfiguration.getEnvironment()), paymentGatewayConfiguration.getMerchantid(),
                paymentGatewayConfiguration.getPublicKey(), paymentGatewayConfiguration.getPrivateKey());

        braintreeGateway.getConfiguration().setTimeout(10000);

        return braintreeGateway;
    }
}
