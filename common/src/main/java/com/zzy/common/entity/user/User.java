package com.zzy.common.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String username;

    private String password;

    private String email;

    private String intro;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-mm-dd")
    private LocalDate birthday;

    private String address;
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    @TableField(fill = FieldFill.INSERT)
    private Integer auth;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime loginTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime registerTime;

    private String headImg;


}
