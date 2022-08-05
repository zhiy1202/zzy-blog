package com.zzy.userserver.service.impl;

import com.zzy.common.entity.user.UserInfo;
import com.zzy.userserver.mapper.UserInfoMapper;
import com.zzy.userserver.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
