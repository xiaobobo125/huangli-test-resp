package com.huangli.web.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.huangli.web.cache.UserCache;
import com.huangli.web.domain.SysUserLogin;
import com.huangli.web.utils.ExpireData;
import com.huangli.web.utils.S;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 简单处理登录验证
 * @Author liuxb
 * @Date 2024/1/6
 **/
@Aspect
@Component
@Order(1)
public class LoginAccessAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LoginAccessAspect.class);

    /**
     * 所有controller下的方法
     */
    @Pointcut("execution(public * com.huangli.web.controller.*Controller.*(..))")
    public void pointCut() {
    }

    /**
     * 登录方法
     */
    @Pointcut("execution(public * com.huangli.web.controller.UserLoginController.login(..))")
    public void login() {
    }

    /**
     * 拦截除【登录方法】之外的，所有controller下的方法
     *
     * @return
     */
    @Around("pointCut() && !login()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        HttpServletRequest request = requestAttributes.getRequest();

        // 获取请求头中的 Authorization 信息
        String token = request.getHeader("X-Token");
        if (S.isNotBlank(token)) {
            ExpireData<SysUserLogin> loginExpireData = UserCache.LOGIN_USER.get(token);
            // 若当前用户已登录，则进行续约
            if (ObjectUtil.isNotNull(loginExpireData) && ObjectUtil.isNotNull(loginExpireData.getValue())) {
                UserCache.LOGIN_USER.put(token, new ExpireData<>(loginExpireData.getValue(), UserCache.DEFAULT_USER_EXPIRE_TIME));
                return joinPoint.proceed();
            }else{
                UserCache.LOGIN_USER.remove(token);
            }
        }

        Class<?> returnType = method.getReturnType();
        Method error = returnType.getMethod("error", String.class);
        return error.invoke(returnType, "未登录系统，禁止访问, 请刷新页面重新登录！");
    }

}
