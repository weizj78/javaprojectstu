package xyz.weizj.common.utils;

import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.entity.user.UserInfo;

public class AuthContextUtil {

    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>() ;


    public static void set(SysUser user){
        threadLocal.set(user);
    }

    public static SysUser get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }


    // 定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    // 删除数据的方法
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }
}
