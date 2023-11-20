package xyz.weizj.manger.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weizj.manger.service.SysRoleService;
import xyz.weizj.model.dto.system.SysRoleDto;
import xyz.weizj.model.entity.system.SysRole;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    SysRoleService roleService;


    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto){

        PageInfo<SysRole> pageInfo =  roleService.findByPage(sysRoleDto,current,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

}
