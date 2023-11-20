package xyz.weizj.manger.service;

import xyz.weizj.model.dto.system.LoginDto;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.vo.system.LoginVo;

public interface SysUserService {
    /**
     *
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    abstract void logOut(String token);


}
