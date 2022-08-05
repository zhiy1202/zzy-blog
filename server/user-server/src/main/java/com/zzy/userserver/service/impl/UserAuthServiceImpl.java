package com.zzy.userserver.service.impl;

import com.zzy.common.entity.user.UserAuth;
import com.zzy.userserver.mapper.UserAuthMapper;
import com.zzy.userserver.service.IUserAuthService;
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
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

}
