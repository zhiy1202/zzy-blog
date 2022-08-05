package com.zzy.blogserver.service.impl;

import com.zzy.blogserver.mapper.BlogMapper;
import com.zzy.blogserver.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.common.entity.blog.Blog;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-07-07
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
