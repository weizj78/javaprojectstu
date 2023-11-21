package xyz.weizj.manger.service;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.dto.system.AssginRoleDto;
import xyz.weizj.model.dto.system.SysRoleDto;
import xyz.weizj.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService {
   public abstract PageInfo findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);
   public abstract Integer saveSysRole(SysRole sysRole);

   public abstract Integer updateSysRole(SysRole sysRole);
   public abstract void deleteById(Long roleId);

   public abstract Map<String,Object> findAllRoles();

   public abstract void doAssgin(AssginRoleDto assginRoleDto);
   public abstract Map<String, Object> findAllRoles(Long userId);

}
