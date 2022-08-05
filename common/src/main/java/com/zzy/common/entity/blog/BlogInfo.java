package com.zzy.common.entity.blog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-07-07
 */
@Getter
@Setter
@TableName("t_blog_info")
@NoArgsConstructor
public class BlogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    private Long blogId;

    private Long lookCount;

    private Long likeCount;

    public BlogInfo(Long blogId, Long lookCount, Long likeCount) {
        this.blogId = blogId;
        this.lookCount = lookCount;
        this.likeCount = likeCount;
    }
}
