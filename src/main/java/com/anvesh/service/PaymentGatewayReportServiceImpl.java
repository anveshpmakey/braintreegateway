package com.anvesh.service;

import com.anvesh.gateway.BraintreePaymentGateway;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amakey on 7/4/17.
 */
@Service
public class PaymentGatewayReportServiceImpl implements PaymentGatewayReportService {

    BraintreeGateway braintreeGateway;

    @Autowired
    BraintreePaymentGateway braintreePaymentGateway;

    @Override
    public void getTransactionsForMerchant() {

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();

        TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest().merchantAccountId().is("melbourneit");

        ResourceCollection<Transaction> transactionResults = braintreeGateway.transaction().search(transactionSearchRequest);

        for (Transaction transaction : transactionResults) {
            System.out.println(transaction.getOrderId());
            System.out.println(transaction.getType());
        }

    }
}
