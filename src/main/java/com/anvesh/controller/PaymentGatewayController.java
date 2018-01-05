package com.anvesh.controller;

import com.anvesh.service.*;
import com.anvesh.util.PaymentGatewayUtils;
import com.anvesh.vo.BrainTreeTranRequest;
import com.anvesh.vo.BraintreeCustomerRequest;
import com.braintreegateway.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by amakey on 6/5/17.
 */
@RestController
public class PaymentGatewayController {

    @Autowired
    PaymentGatewayClientService paymentGatewayClientService;

    @Autowired
    PaymentGatewayTransactionService paymentGatewayTransactionService;

    @Autowired
    PaymentGatewayCustomerService paymentGatewayCustomerService;

    @Autowired
    PaymentGatewayCreditCardService paymentGatewayCreditCardService;

    @Autowired
    PaymentGatewayReportService paymentGatewayReportService;

    BraintreeGateway braintreeGateway;

    @RequestMapping(value="/getAppStatus")
    public String getApplicatoinStatus() {
        return "Braintree Payment Gateway Application started";
    }

    @RequestMapping("/getBraintreeToken")
    public String getBraintreeToken() {

        return paymentGatewayClientService.generateToken();
    }

    @RequestMapping("/createCustomer")
    public String createCustomer(BraintreeCustomerRequest braintreeCustomerRequest) {
        String message = null;

        braintreeCustomerRequest = new BraintreeCustomerRequest();
        braintreeCustomerRequest.setFirstName("Raj");
        braintreeCustomerRequest.setLastName("Srivasthav");
        braintreeCustomerRequest.setCompany("Melbourne IT");
        braintreeCustomerRequest.setEmail("anvesh.makey@melbourneit.com.au");

        message = paymentGatewayCustomerService.createCustomer(braintreeCustomerRequest);

        return message;
    }

    @RequestMapping("/createCustomerWithPaymentMethod")
    public String createCustomerWithPaymentMethod(BraintreeCustomerRequest braintreeCustomerRequest) {
        String message = null;

        braintreeCustomerRequest = new BraintreeCustomerRequest();
        braintreeCustomerRequest.setFirstName("Raj");
        braintreeCustomerRequest.setLastName("Srivasthav");
        braintreeCustomerRequest.setCompany("Melbourne IT");
        braintreeCustomerRequest.setEmail("anvesh.makey@melbourneit.com.au");
        braintreeCustomerRequest.setPaymentMethodNonce("");

        message = paymentGatewayCustomerService.createCustomerWithPaymentMethod(braintreeCustomerRequest);

        return message;
    }

    @RequestMapping("/updateCustomerPayment")
    public String updateCustomerPayment(BraintreeCustomerRequest braintreeCustomerRequest) {
        String message = null;

        braintreeCustomerRequest.setPaymentMethodNonce("");

        message = paymentGatewayCustomerService.updateCustomerPaymentDetails(braintreeCustomerRequest);

        return message;
    }

    @RequestMapping("/saveCreditCard")
    public String saveCreditCard() {
        String message = null;

        Result result = paymentGatewayCreditCardService.saveCreditCard();

        if(result.isSuccess()) {
            message = "Credit card saved successfully";
        } else {
            message = "Credit card save failed";
        }

        System.out.println(result.isSuccess());

        return message;
    }

    @RequestMapping("/preauth")
    public String preauth() {

        String message = null;

        BrainTreeTranRequest brainTreeTranRequest = new BrainTreeTranRequest();

        brainTreeTranRequest.setCardNumber("4111111111111111");
        brainTreeTranRequest.setExpiryMonth("03");
        brainTreeTranRequest.setExpiryYear("2021");
        brainTreeTranRequest.setCvv("123");
        brainTreeTranRequest.setAmount("4567");
        brainTreeTranRequest.setTransactionType("PREAUTH");
        brainTreeTranRequest.setOrderId("Anvesh");

        Result<Transaction> transactionResult = paymentGatewayTransactionService.preauthWithCC(brainTreeTranRequest);

        Transaction transaction = transactionResult.getTarget();

        if(transactionResult.isSuccess()) {
            message = "Preauth successful. Transaction Id - " + transaction.getId();

            System.out.println(transaction.getId());

            System.out.println("Transaction Type - " + transaction.getType() + " " + transaction.toString());

        } else {
            message = "Preauth failed \n";
            message = PaymentGatewayUtils.getTransactionFailedMessage(message, transactionResult);
        }

        return PaymentGatewayUtils.processTransactionResponse(transactionResult);
    }

    @RequestMapping("/settlePayment")
    public String settlePayment() {

        String message = null;

        BrainTreeTranRequest brainTreeTranRequest = new BrainTreeTranRequest();

        brainTreeTranRequest.setCardNumber("4111111111111111");
        brainTreeTranRequest.setExpiryMonth("03");
        brainTreeTranRequest.setExpiryYear("2021");
        brainTreeTranRequest.setCvv("123");
        brainTreeTranRequest.setAmount("1312");
        brainTreeTranRequest.setTransactionType("SALE");

        Result<Transaction> transactionResult = paymentGatewayTransactionService.settleWithCC(brainTreeTranRequest);

        if(transactionResult.isSuccess()) {
            //message = "Transaction settlement successful" + transactionResult.getTransaction().getOrderId();
            message = "Transaction settlement successful " + transactionResult.getTarget().getId();
        } else {
            message = "Transaction settlement failed \n";

            message = PaymentGatewayUtils.getTransactionFailedMessage(message, transactionResult);
        }

        return message;
    }

    @RequestMapping(value = "/settlePreAuthPayment/{transactionId}")
    public String settlePayment(@PathVariable("transactionId") String transactionId) {

        String message = null;
        Result<Transaction> transactionResult = paymentGatewayTransactionService.settlePreauthTransaction(transactionId);

        if (transactionResult.isSuccess()) {

            message = "Transaction settled. Transaction Id - " + transactionResult.getTarget().getId() + " Status - " + transactionResult.getTarget().getStatus();
            System.out.println(transactionResult.getTarget().getId());
        } else {
            System.out.println("Settle failed");
        }

        return "Payment Settled";

    }

    @RequestMapping("/refund")
    public String refundPayment() {
        String message = null;

        Result transactionResult = paymentGatewayTransactionService.refund("b3h44xex");

        if(transactionResult.isSuccess()) {
            message = "Refund Successful";
        } else {
            message = "Refund failed";
        }

        return message;
    }

    @RequestMapping("/findTransaction")
    public String findTransaction() {
        String message = null;

        Transaction transaction = paymentGatewayTransactionService.findTransaction("b3h44xex");

        System.out.println(transaction.toString());

        message = transaction.toString();

        return message;
    }

    @RequestMapping("/getTransactionReport")
    public void getTransactionReport() {

        paymentGatewayReportService.getTransactionsForMerchant();

    }
}
