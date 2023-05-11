package com.zjy.paymentdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.paymentdemo.entity.RefundInfo;
import com.zjy.paymentdemo.mapper.RefundInfoMapper;
import com.zjy.paymentdemo.service.RefundInfoService;
import org.springframework.stereotype.Service;

@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo> implements RefundInfoService {

}
