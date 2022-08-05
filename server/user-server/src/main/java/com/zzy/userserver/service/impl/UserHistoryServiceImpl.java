package com.zzy.userserver.service.impl;

import com.zzy.common.entity.user.UserHistory;
import com.zzy.userserver.mapper.UserHistoryMapper;
import com.zzy.userserver.service.IUserHistoryService;
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
public class UserHistoryServiceImpl extends ServiceImpl<UserHistoryMapper, UserHistory> implements IUserHistoryService {

}
