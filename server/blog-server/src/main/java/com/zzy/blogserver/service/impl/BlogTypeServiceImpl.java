package com.zzy.blogserver.service.impl;

import com.zzy.blogserver.mapper.BlogTypeMapper;
import com.zzy.blogserver.service.IBlogTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.common.entity.blog.BlogType;
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
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper, BlogType> implements IBlogTypeService {

}
