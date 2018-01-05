package com.anvesh.service;

import com.anvesh.vo.BrainTreeTranRequest;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;

import java.math.BigDecimal;

/**
 * Created by amakey on 6/28/17.
 */
public interface PaymentGatewayTransactionService {

    public Result preauthWithCC(BrainTreeTranRequest brainTreeTranRequest);

    public Result preauthWithNonce(BrainTreeTranRequest brainTreeTranRequest);

    public Result settleWithCC(BrainTreeTranRequest brainTreeTranRequest);

    public Result settleWithNonce(BrainTreeTranRequest brainTreeTranRequest);

    public Result settlePreauthTransaction(String transactionReference);

    public Result voidTransaction(String transactionReference);

    public Result refund(String transactionReference);

    public Result partialRefund(String transactionReference, BigDecimal amount);

    public Transaction findTransaction(String transactionReference);

    public String getPaymentMethodNonceForToken(String paymentToken);
}
