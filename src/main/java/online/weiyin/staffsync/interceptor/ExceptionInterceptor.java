package online.weiyin.staffsync.interceptor;

import cn.dev33.satoken.exception.SaTokenException;
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

    /**
     * 鉴权相关异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SaTokenException.class)
    public Result authorizeHandler(SaTokenException e) {
        return Result.failed(e.getMessage(), Code.AUTHORIZE_ERROR);
    }

    /**
     * 通用异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result defaultHandler(Exception e) {
        return Result.failed(e.getMessage(), Code.SERVER_ERROR);
    }
}