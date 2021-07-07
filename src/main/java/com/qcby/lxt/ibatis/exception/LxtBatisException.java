package com.qcby.lxt.ibatis.exception;

/**
 * @className: LxtBatisException
 * @description:
 * @author: lxt
 * @create: 2021-07-06 10:52
 **/
public class LxtBatisException extends RuntimeException{

    public LxtBatisException(String message) {
        super(message);
    }

    public LxtBatisException(String message, Exception e) {
        super(message,e);
    }
}
