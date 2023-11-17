package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.user.UserInfo;

@Mapper
public interface SysUserMapper {
    UserInfo getUSerInfoByUserName(String username);
}
