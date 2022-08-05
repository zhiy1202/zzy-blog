package com.zzy.userserver.util;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author zzy
 * @desc
 */
public class ZZYMailUtil {
    public static SimpleMailMessage getSimpleMailMessage(String to ,String subject ,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("zhiy1202@qq.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        return message;
    }
}
