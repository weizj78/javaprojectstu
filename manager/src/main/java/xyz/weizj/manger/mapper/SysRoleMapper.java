package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.weizj.model.dto.system.SysRoleDto;
import xyz.weizj.model.entity.system.SysRole;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    public abstract List<SysRole> findByPage(SysRoleDto sysRoleDto);

    public abstract Integer saveSysRole(SysRole sysRole);

    public abstract Integer updateSysRole(SysRole sysRole);
    public abstract void deleteById(Long roleId);

    public abstract List<SysRole> findAllRoles();
}
