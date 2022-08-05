package com.zzy.userserver.service.impl;

import com.zzy.common.entity.user.State;
import com.zzy.userserver.mapper.StateMapper;
import com.zzy.userserver.service.IStateService;
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
public class StateServiceImpl extends ServiceImpl<StateMapper, State> implements IStateService {

}
