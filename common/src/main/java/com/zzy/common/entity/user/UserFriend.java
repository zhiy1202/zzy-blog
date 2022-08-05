package com.zzy.common.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzy
 * @since 2022-07-02
 */
@Getter
@Setter
@TableName("t_user_friend")
@NoArgsConstructor
public class UserFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uf_id", type = IdType.AUTO)
    private Long ufId;

    private Long userOne;

    private Long userTwo;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public UserFriend(Long userOne, Long userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
    }
}
