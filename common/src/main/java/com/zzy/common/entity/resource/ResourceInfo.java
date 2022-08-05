package com.zzy.common.entity.resource;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
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
@TableName("t_resource_info")
@NoArgsConstructor
public class ResourceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "download_id", type = IdType.AUTO)
    private Long downloadId;

    private Long resourceId;

    @TableField(fill = FieldFill.INSERT)
    private Long downloadCount;

    public ResourceInfo(Long resourceId) {
        this.resourceId = resourceId;
    }
}
