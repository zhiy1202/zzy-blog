package com.zzy.common;

/**
 * @author zzy
 * @desc 用户权限
 */
public interface UserAuth {
    int userAuth = 1; //已登录
    int adminAuth = 2; //管理员
    int god = 3;
}
