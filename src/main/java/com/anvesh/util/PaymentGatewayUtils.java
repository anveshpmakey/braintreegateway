package com.anvesh.util;

import com.braintreegateway.*;

import java.util.List;

/**
 * Created by anvesh.makey on 9/10/17.
 */
public class PaymentGatewayUtils {

    public static String receivePaymentMethodNonce() {
        String nonceFromtheClient = "fake-valid-mastercard-nonce";

        return nonceFromtheClient;
    }

    public static String getTransactionFailedMessage(String message, Result transactionResult) {
        if(transactionResult.getErrors() != null && transactionResult.getErrors().size() > 0) {
            ValidationErrors validationErrors = transactionResult.getErrors();
            List<ValidationError> validationErrorList = validationErrors.getAllValidationErrors();
            for(ValidationError validationError: validationErrorList) {
                message = message + validationError.getMessage();
            }
        }
        return message;
    }

    public static String processCustomerResponse(Result result) {

        String message = null;

        if (result.isSuccess()) {
            message = "Customer created successfully.";
            Customer customer = (Customer) result.getTarget();
            message = message + customer.getId();
        } else {
            message = "Customer creation failed";
        }

        return message;

    }

    public static String processTransactionResponse(Result result) {

        String message = null;

        if (result.isSuccess()) {
            message = "Customer created successfully.";
            Transaction transaction = result.getTransaction();
            message = message + transaction.getId();
        } else {
            message = "Transactionss creation failed";
        }

        return message;

    }

}
