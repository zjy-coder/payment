package com.zjy.paymentdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.paymentdemo.entity.OrderInfo;
import com.zjy.paymentdemo.entity.Product;
import com.zjy.paymentdemo.enums.OrderStatus;
import com.zjy.paymentdemo.mapper.OrderInfoMapper;
import com.zjy.paymentdemo.mapper.ProductMapper;
import com.zjy.paymentdemo.service.OrderInfoService;
import com.zjy.paymentdemo.utils.OrderNoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private AlipayClient alipayClient;

    @Override
    public OrderInfo createOrderByProductId(Long productId) {
        //查找未支付的订单
        OrderInfo orderInfo = this.selectNoPayOrderByProductId(productId);
        if(orderInfo != null){
            return orderInfo;
        }
        Product product = productMapper.selectById(productId);
        //生成订单
        orderInfo = new OrderInfo();
        orderInfo.setTitle(product.getTitle());
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo());
        orderInfo.setProductId(product.getId());
        orderInfo.setTotalFee(product.getPrice());
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType());
        baseMapper.insert(orderInfo);
        return orderInfo;
    }

    private OrderInfo selectNoPayOrderByProductId(Long productId) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("order_status", OrderStatus.NOTPAY.getType());
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 未支付的订单主动去支付宝查询订单状态
     * 同步订单状态
     */
    @PostConstruct
    public void asyncOrderInfo() throws AlipayApiException {
        //查找未支付的订单
        List<OrderInfo> orderInfoList = lambdaQuery().eq(OrderInfo::getOrderStatus, OrderStatus.NOTPAY.getType()).list();
        for (OrderInfo orderInfo : orderInfoList) {
            String orderNo = orderInfo.getOrderNo();
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no",orderNo);
            request.setBizContent(bizContent.toString());
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                if(response.getTradeStatus().equals("TRADE_SUCCESS")){
                    orderInfo.setOrderStatus(OrderStatus.SUCCESS.getType());
                    baseMapper.updateById(orderInfo);
                }
            }
        }
    }
}
