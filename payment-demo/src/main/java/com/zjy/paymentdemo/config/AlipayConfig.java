package com.zjy.paymentdemo.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:alipay.properties")
@ConfigurationProperties(prefix="alipay")
@Data
@Slf4j
public class AlipayConfig {
    private String appId;
    private String merchantPrivateKey;
    private String alipayPublicKey;
    private String gatewayUrl;
    private String contentKey;
    private String returnUrl;
    private String notifyUrl;

    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {
        com.alipay.api.AlipayConfig alipayConfig = new com.alipay.api.AlipayConfig();
        //支付宝网关地址
        alipayConfig.setServerUrl(gatewayUrl);
        //应用appid
        alipayConfig.setAppId(appId);
        //应用私钥
        alipayConfig.setPrivateKey(merchantPrivateKey);
        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
        //支付宝公钥
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        return new DefaultAlipayClient(alipayConfig);
    }
}
