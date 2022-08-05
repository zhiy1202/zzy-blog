package com.zzy.common.entity.chat;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzy
 * @since 2022-07-09
 */
@Getter
@Setter
@TableName("t_user_chat")
@NoArgsConstructor
public class UserChat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uc_id", type = IdType.AUTO)
    private Long ucId;

    private Long selfId;

    private Long friendId;

    private Long sendId;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public UserChat(Long selfId, Long friendId, Long sendId, String content) {
        this.selfId = selfId;
        this.friendId = friendId;
        this.sendId = sendId;
        this.content = content;
    }
}
