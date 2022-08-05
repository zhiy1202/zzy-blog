package com.zzy.common.entity.blog;

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
 * @since 2022-07-07
 */
@Getter
@Setter
@TableName("t_blog")
@NoArgsConstructor
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "blog_id", type = IdType.AUTO)
    private Long blogId;

    private Long userId;

//    @TableField(fill = FieldFill.INSERT)
    private Long userDir;

    private String title;

    private String intro;

    private String content;

    private Integer typeId;

    private Integer privateId;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDel;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public Blog(String title, String intro, String content, Integer typeId, Integer privateId) {
        this.title = title;
        this.intro = intro;
        this.content = content;
        this.typeId = typeId;
        this.privateId = privateId;
    }
}
