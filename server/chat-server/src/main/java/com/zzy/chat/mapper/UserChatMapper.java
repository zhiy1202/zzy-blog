package com.zzy.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.common.entity.chat.UserChat;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-07-09
 */
@Mapper
public interface UserChatMapper extends BaseMapper<UserChat> {

}
