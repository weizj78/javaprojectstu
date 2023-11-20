package xyz.weizj.manger.service;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.dto.system.SysRoleDto;

public interface SysRoleService {
   public abstract PageInfo findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);
}
