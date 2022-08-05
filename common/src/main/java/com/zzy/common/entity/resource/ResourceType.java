package com.zzy.common.entity.resource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
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
@TableName("t_resource_type")
public class ResourceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rt_id", type = IdType.AUTO)
    private Long rtId;

    private String typeName;

    public ResourceType(String typeName) {
        this.typeName = typeName;
    }
}
