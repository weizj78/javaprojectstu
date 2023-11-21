package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.manger.service.SysUserService;
import xyz.weizj.model.dto.system.SysUserDto;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.entity.user.UserInfo;

import java.util.List;

@Mapper
public interface SysUserMapper {
    SysUser getUSerInfoByUserName(String username);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    SysUser findByUserName(String name);
    void saveSysUser(SysUser sysUser);

    Integer updateSysUser(SysUser sysUser);

    public abstract void deleteById(Long userId);
}
