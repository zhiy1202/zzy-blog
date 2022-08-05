package com.zzy.common.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzy
 * @desc
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZZYResult<T> {
    private int StatusCode;
    private String message;
    private T data;

    public static <T> ZZYResult<T> getInstance(int statusCode, String message , T t){
        return new ZZYResult<T>(statusCode, message, t);
    }
}
