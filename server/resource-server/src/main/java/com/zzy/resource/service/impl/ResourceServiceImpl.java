package com.zzy.resource.service.impl;

import com.zzy.common.entity.resource.Resource;
import com.zzy.resource.mapper.ResourceMapper;
import com.zzy.resource.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-07-08
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
