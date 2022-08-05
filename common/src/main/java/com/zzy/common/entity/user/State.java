package com.zzy.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
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
@TableName("t_state")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "state_id", type = IdType.AUTO)
    private Integer stateId;

    private String stateName;

    public State(String stateName) {
        this.stateName = stateName;
    }
}
