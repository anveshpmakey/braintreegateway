package com.anvesh.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by amakey on 6/29/17.
 */
@Component
@ConfigurationProperties("braintree")
public class PaymentGatewayConfiguration {

    @Value("${braintree.environment}")
    private String environment;

    @Value("${braintree.merchantid")
    private String merchantid;

    @Value("${braintree.privatekey}")
    private String privateKey;

    @Value("${braintree.publickey}")
    private String publicKey;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
