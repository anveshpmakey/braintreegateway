package com.anvesh.service;

import com.anvesh.gateway.BraintreePaymentGateway;
import com.anvesh.vo.BrainTreeTranRequest;
import com.braintreegateway.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by anvesh.makey on 30/10/17.
 */
public class PaypalPaymentGatewayServiceImpl implements PaypalPaymentGatewayService {

    @Autowired
    BraintreeGateway braintreeGateway;

    @Autowired
    BraintreePaymentGateway braintreePaymentGateway;



}
