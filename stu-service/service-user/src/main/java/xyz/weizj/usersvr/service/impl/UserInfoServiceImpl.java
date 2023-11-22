package xyz.weizj.usersvr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import xyz.weizj.common.exception.StuProjectException;
import xyz.weizj.model.dto.h5.UserLoginDto;
import xyz.weizj.model.dto.h5.UserRegisterDto;
import xyz.weizj.model.entity.user.UserInfo;
import xyz.weizj.model.vo.common.ResultCodeEnum;
import xyz.weizj.model.vo.h5.UserInfoVo;
import xyz.weizj.usersvr.mapper.UserInfoMapper;
import xyz.weizj.usersvr.service.UserInfoService;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterDto userRegisterDto) {

        // 获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //校验参数
        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(nickName) ||
                StringUtils.isEmpty(code)) {
            throw new StuProjectException(ResultCodeEnum.DATA_ERROR);
        }

        //校验校验验证码
        String codeValueRedis = redisTemplate.opsForValue().get("phone:code:" + username);
        if(!code.equals(codeValueRedis)) {
            throw new StuProjectException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(null != userInfo) {
            throw new StuProjectException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //保存用户信息
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.save(userInfo);

        // 删除Redis中的数据
        redisTemplate.delete("phone:code:" + username) ;
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //校验参数
        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password)) {
            throw new StuProjectException(ResultCodeEnum.DATA_ERROR);
        }

        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(null == userInfo) {
            throw new StuProjectException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验密码
        String md5InputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!md5InputPassword.equals(userInfo.getPassword())) {
            throw new StuProjectException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new StuProjectException(ResultCodeEnum.ACCOUNT_STOP);
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:spzx:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        String userInfoJSON = redisTemplate.opsForValue().get("user:spzx:" + token);
        if(StringUtils.isEmpty(userInfoJSON)) {
            throw new StuProjectException(ResultCodeEnum.LOGIN_AUTH) ;
        }
        UserInfo userInfo = JSON.parseObject(userInfoJSON , UserInfo.class) ;
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo ;
    }
}