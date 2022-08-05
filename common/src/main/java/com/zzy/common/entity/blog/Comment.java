package com.zzy.common.entity.blog;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzy
 * @since 2022-07-07
 */
@Getter
@Setter
@TableName("t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comm_id", type = IdType.AUTO)
    private Long commId;

    private Long userId;

    private Long blogId;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
