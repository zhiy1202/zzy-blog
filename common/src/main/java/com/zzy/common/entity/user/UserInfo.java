package com.zzy.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
@TableName("t_user_info")
@NoArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    private Long userId;

    private Long blogCount;

    private Long lookCount;

    private Long likeCount;

    private Long commCount;

    public UserInfo(Long userId, Long blogCount, Long lookCount, Long likeCount, Long commCount) {
        this.userId = userId;
        this.blogCount = blogCount;
        this.lookCount = lookCount;
        this.likeCount = likeCount;
        this.commCount = commCount;
    }
}
