package com.anvesh.service;

import com.anvesh.gateway.BraintreePaymentGateway;
import com.anvesh.vo.BrainTreeTranRequest;
import com.braintreegateway.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by amakey on 6/28/17.
 */
@Service
public class PaymentGatewayTransactionServiceImpl implements PaymentGatewayTransactionService {

    BraintreeGateway braintreeGateway;

    @Autowired
    BraintreePaymentGateway braintreePaymentGateway;

    @Override
    public Result preauthWithCC(BrainTreeTranRequest brainTreeTranRequest) {

        TransactionRequest transactionRequest = new TransactionRequest()
                .creditCard()
                .number(brainTreeTranRequest.getCardNumber())
                .cvv(brainTreeTranRequest.getCvv())
                .expirationMonth(brainTreeTranRequest.getExpiryMonth())
                .expirationYear(brainTreeTranRequest.getExpiryYear())
                .done()
                .amount(new BigDecimal(brainTreeTranRequest.getAmount()))
                .options()
                .submitForSettlement(false)
                .done();

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();
        Result<Transaction> transactionResult = braintreeGateway.transaction().sale(transactionRequest);

        return transactionResult;

    }

    @Override
    public Result preauthWithNonce(BrainTreeTranRequest brainTreeTranRequest) {

        TransactionRequest transactionRequest = new TransactionRequest()
                .paymentMethodNonce(brainTreeTranRequest.getPaymentMethodNonce())
                .amount(new BigDecimal(brainTreeTranRequest.getAmount()))
                .options()
                .submitForSettlement(false)
                .done();

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();
        Result<Transaction> transactionResult = braintreeGateway.transaction().sale(transactionRequest);

        return transactionResult;

    }

    @Override
    public Result settleWithCC(BrainTreeTranRequest brainTreeTranRequest) {

        TransactionRequest transactionRequest = new TransactionRequest()
                .creditCard()
                .number(brainTreeTranRequest.getCardNumber())
                .cvv(brainTreeTranRequest.getCvv())
                .expirationMonth(brainTreeTranRequest.getExpiryMonth())
                .expirationYear(brainTreeTranRequest.getExpiryYear())
                .done()
                .amount(new BigDecimal(brainTreeTranRequest.getAmount()))
                .options()
                .submitForSettlement(true)
                .done();

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();
        Result<Transaction> transactionResult = braintreeGateway.transaction().sale(transactionRequest);

        return transactionResult;
    }

    @Override
    public Result settleWithNonce(BrainTreeTranRequest brainTreeTranRequest) {

        TransactionRequest transactionRequest = new TransactionRequest().paymentMethodNonce(brainTreeTranRequest.getPaymentMethodNonce())
                .amount(new BigDecimal(brainTreeTranRequest.getAmount()))
                .options()
                .submitForSettlement(true)
                .done();

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();
        Result<Transaction> transactionResult = braintreeGateway.transaction().sale(transactionRequest);

        return transactionResult;
    }

    @Override
    public Result settlePreauthTransaction(String transactionReference) {

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();

        Result<Transaction> transactionResult = braintreeGateway.transaction().submitForSettlement(transactionReference);

        return transactionResult;
    }

    @Override
    public Result voidTransaction(String transactionReference) {

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();

        Result<Transaction> transactionResult = braintreeGateway.transaction().voidTransaction(transactionReference);

        return transactionResult;

    }

    @Override
    public Result refund(String transactionReference) {

        return braintreePaymentGateway.connectToBraintreeGateway().transaction().refund(transactionReference);

    }

    @Override
    public Result partialRefund(String transactionReference, BigDecimal amount) {
        return braintreePaymentGateway.connectToBraintreeGateway().transaction().refund(transactionReference, amount);
    }

    @Override
    public Transaction findTransaction(String transactionReference) {
        return  braintreePaymentGateway.connectToBraintreeGateway().transaction().find(transactionReference);
    }

    @Override
    public String getPaymentMethodNonceForToken(String paymentToken) {
        String paymentMethodNonce = null;

        braintreeGateway = braintreePaymentGateway.connectToBraintreeGateway();

        Result<PaymentMethodNonce> result = braintreeGateway.paymentMethodNonce().create(paymentToken);
        return paymentMethodNonce;
    }


}
