package com.zzy.userserver.service.impl;

import com.zzy.common.entity.user.UserState;
import com.zzy.userserver.mapper.UserStateMapper;
import com.zzy.userserver.service.IUserStateService;
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
public class UserStateServiceImpl extends ServiceImpl<UserStateMapper, UserState> implements IUserStateService {

}
