package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.entity.user.UserInfo;

@Mapper
public interface SysUserMapper {
    SysUser getUSerInfoByUserName(String username);
}
