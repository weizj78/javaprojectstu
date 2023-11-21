package xyz.weizj.manger.service;

import xyz.weizj.model.entity.system.SysMenu;
import xyz.weizj.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    public List<SysMenu> findNodes();

    public void save(SysMenu sysMenu);
    public void updateById(SysMenu sysMenu);

    public void removeById(Long id);

    public List<SysMenuVo> findUserMenuList();

}
