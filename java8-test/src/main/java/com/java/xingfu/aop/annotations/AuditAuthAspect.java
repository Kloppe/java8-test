package com.java.xingfu.aop.annotations;

import com.java.xingfu.common.enumtype.ErrorCodeEnum;
import com.java.xingfu.common.utils.BizExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 掘金
 * @date 2018/11/21
 * @desc
 */
@Aspect
@Component
public class AuditAuthAspect {

    @Pointcut(value = "@annotation(com.java.xingfu.aop.annotations.AuditAuth)")
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void validate(JoinPoint point){

        //校验
        if(point.getTarget().getClass().getSimpleName().equals("xxx")){

           return;
        }
        throw BizExceptionUtils.build(ErrorCodeEnum.USER_NOT_LOGIN);

    }
}



