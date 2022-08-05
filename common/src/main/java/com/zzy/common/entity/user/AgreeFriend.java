package com.zzy.common.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
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
@TableName("t_agree_friend")
@AllArgsConstructor
@NoArgsConstructor
public class AgreeFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "af_id", type = IdType.AUTO)
    private Long afId;

    private Long sendId;

    private Long agreeId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public AgreeFriend(Long sendId, Long agreeId) {
        this.sendId = sendId;
        this.agreeId = agreeId;
    }
}
