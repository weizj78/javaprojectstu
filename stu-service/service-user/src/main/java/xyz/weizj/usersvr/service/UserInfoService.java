package xyz.weizj.usersvr.service;

import xyz.weizj.model.dto.h5.UserLoginDto;
import xyz.weizj.model.dto.h5.UserRegisterDto;
import xyz.weizj.model.vo.h5.UserInfoVo;

// 业务接口
public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    public UserInfoVo getCurrentUserInfo(String token);
}
