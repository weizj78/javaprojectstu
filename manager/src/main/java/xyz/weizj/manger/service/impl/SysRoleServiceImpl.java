package xyz.weizj.manger.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.weizj.manger.mapper.SysRoleMapper;
import xyz.weizj.manger.service.SysRoleService;
import xyz.weizj.model.dto.system.SysRoleDto;
import xyz.weizj.model.entity.system.SysRole;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

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
}
