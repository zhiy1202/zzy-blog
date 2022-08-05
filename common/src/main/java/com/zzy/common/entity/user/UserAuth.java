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
@TableName("t_user_auth")
@NoArgsConstructor
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ua_id", type = IdType.AUTO)
    private Integer uaId;

    private String auth;

    private String intro;

    public UserAuth(String auth, String intro) {
        this.auth = auth;
        this.intro = intro;
    }
}
