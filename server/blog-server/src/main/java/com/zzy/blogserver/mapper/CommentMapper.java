package com.zzy.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.common.entity.blog.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-07-07
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
