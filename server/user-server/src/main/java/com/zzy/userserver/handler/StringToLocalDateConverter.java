package com.zzy.userserver.handler;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author zzy
 * @desc String 转换 LocalDate
 */
public class StringToLocalDateConverter implements Converter<String,LocalDate> {

    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
