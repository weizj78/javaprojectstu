package xyz.weizj.manger.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weizj.manger.ManagerApplication;
import xyz.weizj.manger.service.SysRoleService;
import xyz.weizj.model.dto.system.AssginRoleDto;
import xyz.weizj.model.dto.system.SysRoleDto;
import xyz.weizj.model.entity.system.SysRole;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;

import java.util.Map;

@RestController
@Tag(name = "角色管理")
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    SysRoleService roleService;
    @Operation(summary = "用户角色列表")
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto){

        PageInfo<SysRole> pageInfo =  roleService.findByPage(sysRoleDto,current,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "新增用户角色信息")
    @PostMapping(value = "/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole){
        Integer integer = roleService.saveSysRole(sysRole);
        if (integer == 0){
            return Result.build(null,ResultCodeEnum.SYSTEM_ERROR);
        }
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping(value = "/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        roleService.updateSysRole(sysRole) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping(value = "/deleteById/{roleId}")
    public Result deleteById(@PathVariable(value = "roleId") Long roleId) {
        roleService.deleteById(roleId) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @GetMapping(value = "/findAllRoles")
    public Result<Map<String,Object>> findAllRoles(){
        Map<String,Object> allRoulesMap = roleService.findAllRoles();
        return Result.build(allRoulesMap,ResultCodeEnum.SUCCESS);
    }
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto){
        roleService.doAssgin(assginRoleDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @GetMapping(value = "/findAllRoles/{userId}")
    public Result<Map<String , Object>> findAllRoles(@PathVariable(value = "userId") Long userId) {
        Map<String, Object> resultMap = roleService.findAllRoles(userId);
        return Result.build(resultMap , ResultCodeEnum.SUCCESS)  ;
    }
}
