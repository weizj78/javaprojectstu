package xyz.weizj.manger.service;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.dto.system.LoginDto;
import xyz.weizj.model.dto.system.SysUserDto;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.vo.system.LoginVo;

public interface SysUserService {
    /**
     *
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto);

    abstract SysUser getUserInfo(String token);

    abstract void logOut(String token);

    abstract PageInfo findByPage(SysUserDto sysUserDto,Integer pageSize,Integer pageNum);

    public abstract void saveSysUser(SysUser sysUser);

    public abstract Integer updateSysUser(SysUser sysUser);

    public abstract void deleteById(Long userId);

}
