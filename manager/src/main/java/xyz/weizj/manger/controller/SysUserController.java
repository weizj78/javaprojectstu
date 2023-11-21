package xyz.weizj.manger.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weizj.manger.service.SysUserService;
import xyz.weizj.model.dto.system.SysUserDto;
import xyz.weizj.model.entity.system.SysRole;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;

@Tag(name = "用户管理")
@RestController
@RequestMapping(value = "/admin/system/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    public Result<PageInfo<SysRole>> findByPage(SysUserDto sysUserDto,
                                                @PathVariable(value="pageNum") Integer pageNum,
                                                @PathVariable(value = "pageSize") Integer pageSize){
        PageInfo<SysUser> pageInfo =  sysUserService.findByPage(sysUserDto,pageNum,pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // com.atguigu.spzx.manager.controller.SysUserController
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    // com.atguigu.spzx.manager.controller.SysUserController
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping(value = "/deleteById/{userId}")
    public Result deleteById(@PathVariable(value = "userId") Long userId) {
        sysUserService.deleteById(userId) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
