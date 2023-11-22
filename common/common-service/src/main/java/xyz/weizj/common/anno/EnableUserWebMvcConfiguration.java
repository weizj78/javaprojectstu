package xyz.weizj.common.anno;


import org.springframework.context.annotation.Import;
import xyz.weizj.common.config.UserWebMvcConfiguration;
import xyz.weizj.common.interceptor.UserLoginAuthInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = { UserLoginAuthInterceptor.class , UserWebMvcConfiguration.class})
public @interface EnableUserWebMvcConfiguration {
}
