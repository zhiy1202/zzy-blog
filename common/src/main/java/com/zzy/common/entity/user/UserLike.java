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
@TableName("t_user_like")
@NoArgsConstructor
public class UserLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "like_id", type = IdType.AUTO)
    private Long likeId;

    private Long userId;

    private Long blogId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public UserLike(Long userId, Long blogId) {
        this.userId = userId;
        this.blogId = blogId;
    }
}
