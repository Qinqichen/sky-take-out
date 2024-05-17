package com.sky.aspect;


import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {



    // 切点

    /**
     * 任何类型 com.sky.mapper.类.方法.(任何参数) and 包含AutoFill的注解
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill) ")
    public void autoFillPointCut(){}


    /**
     * 前置通知
     * @param joinpoint
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinpoint){

        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        //获取注解信息
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);

        //获取操作类型
        OperationType operationType = annotation.value();

        Object[] args = joinpoint.getArgs();

        if (args == null || args.length == 0) {
            return;
        }

        Object entity = args[0];

        //获取设置参数
        LocalDateTime localDateTime = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        if (operationType.equals(OperationType.INSERT)) {
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setCreateTime.invoke(entity,localDateTime);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,localDateTime);
                setUpdateUser.invoke(entity,currentId);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if (operationType.equals(OperationType.UPDATE)) {
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity,localDateTime);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
