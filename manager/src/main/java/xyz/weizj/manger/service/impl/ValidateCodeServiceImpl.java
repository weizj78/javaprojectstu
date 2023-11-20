package xyz.weizj.manger.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.weizj.manger.service.ValidateCodeService;
import xyz.weizj.model.vo.system.ValidateCodeVo;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    private final static String imageHeader = "data:image/png;base64,";
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {
        // 1. 通过工具生成图片验证码 hutool
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        // 2.把验证码存储到redis,redis的key uuid redis value:验证码的值
        String codeValue = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        String key = UUID.randomUUID().toString().replaceAll("-","");
        redisTemplate.opsForValue().set(key,codeValue, 5,TimeUnit.MINUTES);
        // 3. 返回validateVO
        ValidateCodeVo codeVo = new ValidateCodeVo();
        codeVo.setCodeKey(key);
        codeVo.setCodeValue(imageHeader+imageBase64);
        return codeVo;
    }
}
