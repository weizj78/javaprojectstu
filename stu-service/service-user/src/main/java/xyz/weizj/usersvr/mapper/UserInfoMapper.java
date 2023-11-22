package xyz.weizj.usersvr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.weizj.model.entity.user.UserInfo;

@Mapper
public interface UserInfoMapper {
    void save(UserInfo userInfo);

    UserInfo getByUsername(@Param("username") String username);

}
