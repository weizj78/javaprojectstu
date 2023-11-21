package xyz.weizj.manger.service.impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.weizj.common.utils.AuthContextUtil;
import xyz.weizj.manger.helper.MenuHelper;
import xyz.weizj.manger.mapper.SysMenuMapper;
import xyz.weizj.manger.mapper.SysRoleMenuMapper;
import xyz.weizj.manger.service.SysMenuService;
import xyz.weizj.manger.service.SysRoleMenuService;
import xyz.weizj.model.dto.system.AssginMenuDto;
import xyz.weizj.model.entity.system.SysMenu;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.vo.system.SysMenuVo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {

        // 查询所有的菜单数据
        List<SysMenu> sysMenuList = sysMenuService.findNodes() ;

        // 查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId) ;

        // 将数据存储到Map中进行返回
        Map<String , Object> result = new HashMap<>() ;
        result.put("sysMenuList" , sysMenuList) ;
        result.put("roleMenuIds" , roleMenuIds) ;

        // 返回
        return result;
    }

    @Transactional
    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {

        // 根据角色的id删除其所对应的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());

        // 获取菜单的id
        List<Map<String, Number>> menuInfo = assginMenuDto.getMenuIdList();
        if(menuInfo != null && menuInfo.size() > 0) {
            sysRoleMenuMapper.doAssign(assginMenuDto) ;
        }
    }
}