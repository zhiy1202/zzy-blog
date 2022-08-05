package com.zzy.common.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("t_user_state")
@NoArgsConstructor
public class UserState implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "us_id", type = IdType.AUTO)
    private Long usId;

    private Long userId;

    private Integer stateId;

    private String intro;

    private LocalDateTime continueTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public UserState(Long userId, Integer stateId, String intro, LocalDateTime continueTime) {
        this.userId = userId;
        this.stateId = stateId;
        this.intro = intro;
        this.continueTime = continueTime;
    }
}
