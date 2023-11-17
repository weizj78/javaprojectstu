package xyz.weizj.manger.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.weizj.manger.service.SysUserService;
import xyz.weizj.model.dto.system.LoginDto;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;
import xyz.weizj.model.vo.system.LoginVo;

@RestController
@Tag(name="用户接口")
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    SysUserService userService;

    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = userService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
}
