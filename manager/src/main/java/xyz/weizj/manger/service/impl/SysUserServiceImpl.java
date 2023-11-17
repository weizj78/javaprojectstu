package xyz.weizj.manger.service.impl;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xyz.weizj.common.exception.StuProjectException;
import xyz.weizj.manger.mapper.SysUserMapper;
import xyz.weizj.manger.service.SysUserService;
import xyz.weizj.model.dto.system.LoginDto;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.entity.user.UserInfo;
import xyz.weizj.model.vo.common.ResultCodeEnum;
import xyz.weizj.model.vo.system.LoginVo;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper userMapper;

    @Autowired
    protected RedisTemplate<String,String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 1.获取用户名
        String userName = loginDto.getUserName();
        // 2.根据用户名获取用户信息
        SysUser userInfo = userMapper.getUSerInfoByUserName(userName);
        // 3.根据用户名查询不到用户信息
        if (userInfo == null) {
//            throw  new RuntimeException("用户名不存在");
            throw new StuProjectException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 4.用户名存在
        String dataBasePassword = userInfo.getPassword();
        String inputPassword = loginDto.getPassword();
        inputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        // 5.获取输入密码，比较密码
        if(!inputPassword.equals(dataBasePassword)){
//            throw new RuntimeException("密码不正确");
            throw new StuProjectException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 6,登录成功，生成token
        String token = UUID.randomUUID().toString().replaceAll("-","");
        // 7.把登录成功用户信息放到redis
        redisTemplate.opsForValue().set("user:login"+token,JSON.toJSONString(userInfo),7, TimeUnit.DAYS);
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }
}
