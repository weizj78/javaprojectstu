package xyz.weizj.manger.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.weizj.common.utils.AuthContextUtil;
import xyz.weizj.model.entity.system.SysUser;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求方式
        String method = request.getMethod();
        // 2. 如果请求方式是options, 预检请求
        if ("OPTIONS".equals(method)){
            return true;
        }
        //3.从请求头获取token
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)){
            responseNoLoginInfo(response);
            return false;
        }
        //4. 如果token不为空，拿着token查询redis
        String userJson = redisTemplate.opsForValue().get("user:login"+token);
        // 5.如果redis查询不到数据，返回错误提示
        if (StrUtil.isEmpty(userJson)){
            responseNoLoginInfo(response);
            return false;
        }
        //6.查询到用户信息，把用户信息放入threadlocal
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        AuthContextUtil.set(sysUser);
        //7.把redis用户信息数据更新过期时间
        redisTemplate.expire("user:login"+token,30, TimeUnit.MINUTES);
        //8.放行
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // ThreadLocal数据删除
        AuthContextUtil.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
