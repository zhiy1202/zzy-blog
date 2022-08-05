package com.zzy.userserver.handler;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zzy
 * @desc
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
