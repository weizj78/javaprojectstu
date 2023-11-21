package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.dto.system.AssginMenuDto;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    public abstract List<Long> findSysRoleMenuByRoleId(Long roleId);

    public abstract void deleteByRoleId(Long roleId);
    public abstract void doAssign(AssginMenuDto assginMenuDto);

    public abstract  void updateSysRoleMenuIsHalf(Long menuId);
}
