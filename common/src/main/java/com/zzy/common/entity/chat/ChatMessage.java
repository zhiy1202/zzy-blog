package com.zzy.common.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zzy
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {
    private Long userId;
    private Long friendId;
    private String message;
}
