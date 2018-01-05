package com.anvesh.service;

import com.anvesh.gateway.BraintreePaymentGateway;
import com.anvesh.util.PaymentGatewayUtils;
import com.anvesh.vo.BraintreeCustomerRequest;
import com.braintreegateway.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amakey on 6/28/17.
 */
@Service
public class PaymentGatewayCustomerServiceImpl implements PaymentGatewayCustomerService {

    BraintreeGateway braintreeGateway;

    @Autowired
    BraintreePaymentGateway braintreePaymentGateway;

    @Override
    public String createCustomer(BraintreeCustomerRequest braintreeCustomerRequest) {

        String message = null;
        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();

        new TransactionSearchRequest().merchantAccountId();

        CustomerRequest customerRequest = new CustomerRequest()
                .firstName(braintreeCustomerRequest.getFirstName())
                .lastName(braintreeCustomerRequest.getLastName())
                .company(braintreeCustomerRequest.getCompany())
                .email(braintreeCustomerRequest.getEmail());

        Result result = braintreeGateway.customer().create(customerRequest);

        return PaymentGatewayUtils.processCustomerResponse(result);
    }

    @Override
    public String createCustomerWithPaymentMethod(BraintreeCustomerRequest braintreeCustomerRequest) {
        String message = null;
        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();

        new TransactionSearchRequest().merchantAccountId();

        CustomerRequest customerRequest = new CustomerRequest()
                .firstName(braintreeCustomerRequest.getFirstName())
                .lastName(braintreeCustomerRequest.getLastName())
                .company(braintreeCustomerRequest.getCompany())
                .email(braintreeCustomerRequest.getEmail())
                .paymentMethodNonce(braintreeCustomerRequest.getPaymentMethodNonce());

        Result result = braintreeGateway.customer().create(customerRequest);

        return PaymentGatewayUtils.processCustomerResponse(result);
    }

    @Override
    public String updateCustomerPaymentDetails(BraintreeCustomerRequest braintreeCustomerRequest) {
        String message = null;

        CustomerRequest request = new CustomerRequest()
                .paymentMethodNonce(braintreeCustomerRequest.getPaymentMethodNonce());

        Result<Customer> updateResult = braintreeGateway.customer().update(braintreeCustomerRequest.getCustomerId(), request);

        return PaymentGatewayUtils.processCustomerResponse(updateResult);
    }

    @Override
    public String findCustomerById(String customerReference) {
        String customerId = null;

        Customer customer = braintreeGateway.customer().find(customerReference);

        return customerId;
    }
}
