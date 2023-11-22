package xyz.weizj.manger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyz.weizj.common.service.AsyncOperLogService;
import xyz.weizj.manger.mapper.SysOperLogMapper;
import xyz.weizj.model.entity.system.SysOperLog;

@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Async      // 异步执行保存日志操作
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }

}
