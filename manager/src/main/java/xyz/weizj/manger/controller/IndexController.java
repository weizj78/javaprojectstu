package xyz.weizj.manger.controller;

import cn.hutool.http.server.HttpServerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weizj.manger.service.SysUserService;
import xyz.weizj.manger.service.ValidateCodeService;
import xyz.weizj.model.dto.system.LoginDto;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;
import xyz.weizj.model.vo.system.LoginVo;
import xyz.weizj.model.vo.system.ValidateCodeVo;

@RestController
@Tag(name="用户接口")
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    SysUserService userService;

    @Autowired
    ValidateCodeService validateCodeService;

//    @GetMapping(value = "/getUserInfo")
//    public Result getUserInfo(HttpServerRequest req){
//        // 1.从请求头获取Token
//        String token = req.getHeader("token");
//    }

    @Operation(summary = "获取用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader String token){
        SysUser user = userService.getUserInfo(token);
        return Result.build(user,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "用户退出登录")
    @GetMapping(value = "/logout")
    public Result logOut(@RequestHeader(name="token") String token){
        userService.logOut(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = userService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取验证码")
    @GetMapping("generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }
}
