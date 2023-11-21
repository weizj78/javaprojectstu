package xyz.weizj.manger.service;

import xyz.weizj.model.dto.system.AssginMenuDto;
import xyz.weizj.model.vo.system.SysMenuVo;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuService {
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    public void doAssign(AssginMenuDto assginMenuDto);
}
