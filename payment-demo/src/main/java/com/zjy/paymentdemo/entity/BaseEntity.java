package com.zjy.paymentdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Date updateTime;

    private Date createTime;
}
