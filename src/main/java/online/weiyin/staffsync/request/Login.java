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

@Data
@AllArgsConstructor
public class Login {

    private String username;

    private String password;
}
