package xyz.weizj.common.service;

import xyz.weizj.model.entity.system.SysOperLog;

public interface AsyncOperLogService {
    public abstract void saveSysOperLog(SysOperLog sysOperLog) ;
}
