package com.zzy.chat.config;

import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzy
 * @desc
 */
public class SessionManage {
    static final ConcurrentHashMap<String, WebSocketSession> sessionManage = new ConcurrentHashMap<>();

    public static void setSession(String userId , WebSocketSession session) {
        sessionManage.put(userId,session);
    }
    public static WebSocketSession getSession(String userId) {
        return sessionManage.get(userId);
    }
    public static void removeSession(String userId){
        sessionManage.remove(userId);
    }
}
