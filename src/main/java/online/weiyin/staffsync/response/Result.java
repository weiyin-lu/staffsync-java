package online.weiyin.staffsync.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @ClassName Result
 * @Description 统一响应格式
 * @Version 1.0.0
 * @Time 2023/11/05 下午 07:28
 * @Author 卢子昂
 */
@Getter
@Setter
@ToString
@ApiModel("统一封装返回集")
public class Result<T> {
    @ApiModelProperty("返回代码")
    private Integer code = 200;
    @ApiModelProperty("返回代码对应的消息")
    private String msg = "default";
    @ApiModelProperty("业务角度的（错误）原因")
    private String cause = null;
    @ApiModelProperty("响应数据资源")
    private T data = null;

    public Result(T data) {
        this.data = data;
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(String msg, T data) {
        this.data = data;
    }

    public Result(Code code) {
        this.msg = code.getMsg();
        this.code = code.getCode();
    }

    public Result(String cause, Code code) {
        this.msg = code.getMsg();
        this.code = code.getCode();
        this.cause = cause;
    }

    /**
     * 返回一条含有参数的消息
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result success(T data) {
        return new Result(data);
    }

    /**
     * 返回一条自定义消息内容的成功消息
     *
     * @param msg 自定义消息内容
     * @param <T>
     * @return
     */
    public static <T> Result success(String msg) {
        return new Result(msg);
    }

    /**
     * 返回一条含有数据的、自定义消息内容的成功消息
     *
     * @param msg  自定义消息内容
     * @param data 数据
     * @param <T>
     * @return
     */
    public static <T> Result success(String msg, T data) {
        return new Result(msg, data);
    }

    /**
     * 返回一条失败消息，必须包含错误类型
     *
     * @param code 错误类型
     * @return
     */
    public static Result failed(Code code) {
        return new Result(code);
    }

    /**
     * 返回一条自定义消息内容的失败消息，必须包含错误类型
     *
     * @param cause 错误原因
     * @param code 错误类型
     * @return
     */
    public static Result failed(String cause, Code code) {
        return new Result(cause, code);
    }
}
