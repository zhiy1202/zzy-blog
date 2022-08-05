package com.zzy.userserver.service.impl;

import com.zzy.common.entity.user.User;
import com.zzy.userserver.mapper.UserMapper;
import com.zzy.userserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-07-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
