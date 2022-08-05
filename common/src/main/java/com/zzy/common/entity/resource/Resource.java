package com.zzy.common.entity.resource;

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
 * @since 2022-07-08
 */
@Getter
@Setter
@TableName("t_resource")
@NoArgsConstructor
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;

    private Long userId;

    private Integer rtId;

    private String title;

    private String remotePath;

    private Integer privateId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public Resource(Long userId, String title, String remotePath, Integer privateId) {
        this.userId = userId;
        this.title = title;
        this.remotePath = remotePath;
        this.privateId = privateId;
    }
}
