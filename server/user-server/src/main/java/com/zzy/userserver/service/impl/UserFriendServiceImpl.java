package com.zzy.userserver.service.impl;

import com.zzy.common.entity.user.UserFriend;
import com.zzy.userserver.mapper.UserFriendMapper;
import com.zzy.userserver.service.IUserFriendService;
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
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements IUserFriendService {

}
