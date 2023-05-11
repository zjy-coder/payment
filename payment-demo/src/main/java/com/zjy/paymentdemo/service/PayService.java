package com.zjy.paymentdemo.service;

public interface PayService {
    /**
     * 下单支付接口
     * @param productId
     * @return
     */
    String tradePagePay(Long productId);
}
