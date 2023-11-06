package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName Login
 * @Description 登录请求dto
 * @Version 1.0.0
 * @Time 2023/11/06 下午 01:59
 * @Author 卢子昂
 */

@Schema(name = "登录请求dto", description = "登录方法使用")
@Data
@AllArgsConstructor
public class Login {

    @Schema(description = "账号")
    private String username;
    @Schema(description = "密码")
    private String password;
}
