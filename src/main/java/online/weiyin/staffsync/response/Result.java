package online.weiyin.staffsync.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "响应结果集", description = "服务器统一响应结果集")
@Getter
@Setter
@ToString
public class Result<T> {
    @Schema(description = "响应编码")
    private Integer code = 200;
    @Schema(description = "编码消息")
    private String msg = "default";
    @Schema(description = "响应原因，在异常响应中携带")
    private String cause = null;
    @Schema(description = "响应数据集")
    private T data = null;

    public Result(T data) {
        this.data = data;
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(String msg, T data) {
        this.msg = msg;
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
     * 返回一条失败消息，包含NOT_FOUND错误类型
     *
     * @return
     */
    public static Result failed() {
        return new Result(Code.NOT_FOUND);
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
     * @param code  错误类型
     * @return
     */
    public static Result failed(String cause, Code code) {
        return new Result(cause, code);
    }
}
