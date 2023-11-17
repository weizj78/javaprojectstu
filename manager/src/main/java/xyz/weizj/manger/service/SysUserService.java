package xyz.weizj.manger.service;

import xyz.weizj.model.dto.system.LoginDto;
import xyz.weizj.model.vo.system.LoginVo;

public interface SysUserService {
    /**
     *
     * @param loginVo
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto);
}
