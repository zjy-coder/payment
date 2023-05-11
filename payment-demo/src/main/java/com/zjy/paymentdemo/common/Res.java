package com.zjy.paymentdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Res {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private Integer code;

    private Object data;

    private String msg;

    public static Res success() {
        return new Res(200,null,SUCCESS);
    }


    public static Res success(Object data) {
        return new Res(200,data,SUCCESS);
    }

    public static Res fail(){
        return new Res(500,null,FAIL);
    }
}
