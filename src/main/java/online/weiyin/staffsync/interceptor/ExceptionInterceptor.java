package online.weiyin.staffsync.interceptor;

import cn.dev33.satoken.exception.NotLoginException;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ExceptionInterceptor
 * @Description 全局异常拦截
 * @Version 1.0.0
 * @Time 2023/11/05 下午 07:27
 * @Author 卢子昂
 */
@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        if (e instanceof NotLoginException) {
            return Result.failed(e.getMessage(), Code.AUTHORIZE_ERROR);
        } else {
            return Result.failed(e.getMessage(), Code.SERVER_ERROR);
        }
    }
}
