package com.zzy.userserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.common.entity.user.UserHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-07-02
 */
@Mapper
public interface UserHistoryMapper extends BaseMapper<UserHistory> {

}
