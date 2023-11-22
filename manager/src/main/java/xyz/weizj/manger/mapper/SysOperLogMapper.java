package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.system.SysOperLog;

@Mapper
public interface SysOperLogMapper {
    public abstract void insert(SysOperLog sysOperLog);
}
