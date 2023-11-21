package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.system.SysMenu;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    public abstract List<SysMenu> selectAll();

    public abstract  void insert(SysMenu sysMenu);

    public abstract void updateById(SysMenu sysMenu);

    public abstract int countByParentId(Long id);
    public abstract void deleteById(Long id);

    public abstract List<SysMenu> selectListByUserId(Long userId);

    public abstract  void updateSysRoleMenuIsHalf(Long menuId);

    public abstract SysMenu selectById(Long id) ;
}