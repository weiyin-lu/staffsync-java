package online.weiyin.staffsync.response;

import lombok.Getter;

/**
 * @ClassName Code
 * @Description 错误类型编码
 * @Version 1.0.0
 * @Time 2023/11/05 下午 07:32
 * @Author 卢子昂
 */
@Getter
public enum Code {
    NOT_FOUND(404, "未找到资源"),
    SERVER_ERROR(500, "服务器错误"),
    AUTHORIZE_ERROR(1001,"鉴权错误"),
    INSERT_ERROR(1002,"添加/插入错误"),
    UPDATE_ERROR(1003,"修改/更新错误"),
    ;

    private Integer code;
    private String msg;

    Code(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
