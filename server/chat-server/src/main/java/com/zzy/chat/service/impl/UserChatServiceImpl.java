package com.zzy.chat.service.impl;

import com.zzy.chat.mapper.UserChatMapper;
import com.zzy.chat.service.IUserChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.common.entity.chat.UserChat;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-07-09
 */
@Service
public class UserChatServiceImpl extends ServiceImpl<UserChatMapper, UserChat> implements IUserChatService {

}
