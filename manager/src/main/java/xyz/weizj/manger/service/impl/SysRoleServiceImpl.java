package xyz.weizj.manger.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.weizj.manger.mapper.SysRoleMapper;
import xyz.weizj.manger.mapper.SysRoleUserMapper;
import xyz.weizj.manger.service.SysRoleService;
import xyz.weizj.model.dto.system.AssginRoleDto;
import xyz.weizj.model.dto.system.SysRoleDto;
import xyz.weizj.model.entity.system.SysRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleUserMapper roleUserMapper;

    @Override
    public PageInfo findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        PageHelper.startPage(current,limit);
        // 根据条件查询所有数据

        List<SysRole> list = roleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(list);
        return sysRolePageInfo;
    }

    @Override
    public Integer saveSysRole(SysRole sysRole) {
        return roleMapper.saveSysRole(sysRole);
    }

    @Override
    public Integer updateSysRole(SysRole sysRole) {
       return roleMapper.updateSysRole(sysRole) ;
    }

    @Override
    public void deleteById(Long roleId) {
        roleMapper.deleteById(roleId) ;
    }

    @Override
    public Map<String, Object> findAllRoles() {
        List<SysRole> allRoles = roleMapper.findAllRoles();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("allRolesList",allRoles);
        return resultMap;
    }

    @Override
    public void doAssgin(AssginRoleDto assginRoleDto) {
        roleUserMapper.deleteByUserId(assginRoleDto.getUserId());
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        roleIdList.forEach(roleId->{
            roleUserMapper.doAssign(assginRoleDto.getUserId(),roleId);
        });
    }

    @Override
    public Map<String, Object> findAllRoles(Long userId) {

        // 查询所有的角色数据
        List<SysRole> sysRoleList = roleMapper.findAllRoles() ;

        // 查询当前登录用户的角色数据
        List<Long> sysRoles = roleUserMapper.findSysUserRoleByUserId(userId);

        // 构建响应结果数据
        Map<String , Object> resultMap = new HashMap<>() ;
        resultMap.put("allRolesList" , sysRoleList) ;
        resultMap.put("sysUserRoles", sysRoles);

        return resultMap;
    }

}
