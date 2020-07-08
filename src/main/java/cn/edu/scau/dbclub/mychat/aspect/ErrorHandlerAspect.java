package cn.edu.scau.dbclub.mychat.aspect;


import cn.edu.scau.dbclub.mychat.result.ErrorCode;
import cn.edu.scau.dbclub.mychat.result.ErrorResponse;
import cn.edu.scau.dbclub.mychat.result.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ErrorHandlerAspect {

    /**
     * 设置拦截点为所有的Controller
     */
    @Pointcut("execution(* cn.edu.scau.dbclub.mychat.controller.*.*.*(..))")
    public void pointCut(){
    }


    /**
     * 对返回结果的错误进行解析，只对返回为Result的进行解析
     * 如果Result成功，就取出其中的数据，进行返回，如果不成
     * 功，封装相对应的ResponseEntity
     *
     * @param joinPoint ProceedingJoinPoint
     * @return Object
     */
    @Around(value = "pointCut()")
    public Object handler(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret = joinPoint.proceed();
        if (!(ret instanceof Result)) {
            return ret;
        }

        Result result = (Result) ret;
        if (result.isSuccess()) {
            return result.getData();
        }

        ErrorCode errorCode = result.getErrorCode();
        String message = result.getMessage();
        ErrorResponse errorResponse;
        if (message == null) {
            errorResponse = new ErrorResponse(errorCode.getError(), errorCode.getMessage());
        } else {
            errorResponse = new ErrorResponse(errorCode.getError(), message);
        }
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }
}
