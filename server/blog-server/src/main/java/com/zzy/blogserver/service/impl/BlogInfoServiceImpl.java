package com.zzy.blogserver.service.impl;

import com.zzy.blogserver.mapper.BlogInfoMapper;
import com.zzy.blogserver.service.IBlogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.common.entity.blog.BlogInfo;
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
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoMapper, BlogInfo> implements IBlogInfoService {

}
