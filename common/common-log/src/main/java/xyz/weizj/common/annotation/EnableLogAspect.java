package xyz.weizj.common.annotation;

import org.springframework.context.annotation.Import;
import xyz.weizj.common.log.LogAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = LogAspect.class)
public @interface EnableLogAspect {
}
