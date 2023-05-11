package com.zjy.paymentdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.paymentdemo.entity.PaymentInfo;
import com.zjy.paymentdemo.mapper.PaymentInfoMapper;
import com.zjy.paymentdemo.service.PaymentInfoService;
import org.springframework.stereotype.Service;

@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

}
