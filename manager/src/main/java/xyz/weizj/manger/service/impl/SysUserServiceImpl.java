package xyz.weizj.manger.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xyz.weizj.common.exception.StuProjectException;
import xyz.weizj.manger.mapper.SysUserMapper;
import xyz.weizj.manger.service.SysUserService;
import xyz.weizj.model.dto.system.LoginDto;
import xyz.weizj.model.dto.system.SysUserDto;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.vo.common.ResultCodeEnum;
import xyz.weizj.model.vo.system.LoginVo;

import java.util.List;
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

        // 1。获取输入验证码与存储到redis的key的名称
        String key = loginDto.getCodeKey();
        String value = redisTemplate.opsForValue().get(key);
        //2。 比较输入验证码与redis存储的验证码是否一致
        if (StrUtil.isEmpty(value) ||!value.equalsIgnoreCase(loginDto.getCaptcha())){
            throw new StuProjectException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //4。验证成功，删除redis的key
        redisTemplate.delete(key);

        // 1.获取用户名
        String userName = loginDto.getUserName();
        // 2.根据用户名获取用户信息
        SysUser userInfo = userMapper.getUSerInfoByUserName(userName);
        // 3.根据用户名查询不到用户信息
        if (userInfo == null) {
            throw new StuProjectException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 4.用户名存在
        String dataBasePassword = userInfo.getPassword();
        String inputPassword = loginDto.getPassword();
        inputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        // 5.获取输入密码，比较密码
        if(!inputPassword.equals(dataBasePassword)){
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

    public SysUser getUserInfo(String token){
        // 1。从redis获取存储的值
        String userJson = redisTemplate.opsForValue().get("user:login" + token);
        if (userJson == null){
            throw new StuProjectException(ResultCodeEnum.LOGIN_AUTH);
        }
        // 2。反序列化未token
        return JSON.parseObject(userJson, SysUser.class);
    }

    @Override
    public void logOut(String token) {
        redisTemplate.delete("user:login" + token);
    }

    @Override
    public PageInfo findByPage(SysUserDto sysUserDto, Integer pageSize, Integer pageNum) {
        List<SysUser> byPage = userMapper.findByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(byPage);
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {

        // 根据输入的用户名查询用户
        SysUser dbSysUser = userMapper.findByUserName(sysUser.getUserName()) ;
        if(dbSysUser != null) {
            throw new StuProjectException(ResultCodeEnum.USER_NAME_IS_EXISTS) ;
        }

        // 对密码进行加密
        String password = sysUser.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(digestPassword);
        sysUser.setStatus(0);
        userMapper.saveSysUser(sysUser) ;
    }

    @Override
    public Integer updateSysUser(SysUser sysUser) {
        return userMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        userMapper.deleteById(userId) ;
    }

}
