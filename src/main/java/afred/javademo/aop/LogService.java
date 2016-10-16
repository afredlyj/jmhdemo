package afred.javademo.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by afred on 16/10/11.
 */
public class LogService implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

//        logger.info("method : {}, {}", method.getName(), Arrays.asList(args));

    }
}
