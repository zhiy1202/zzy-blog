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
@TableName("t_user_dir")
@NoArgsConstructor
public class UserDir implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dir_id", type = IdType.AUTO)
    private Long dirId;

    private Long userId;

    private String dirName;

    @TableField(fill = FieldFill.INSERT)
    private Long blogCount;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public UserDir(Long userId, String dirName) {
        this.userId = userId;
        this.dirName = dirName;
    }
}
