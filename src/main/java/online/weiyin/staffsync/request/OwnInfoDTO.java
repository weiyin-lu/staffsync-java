package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName OwnInfo
 * @Description 个人基本信息dto
 * @Version 1.0.0
 * @Time 2023/11/06 下午 09:53
 * @Author 卢子昂
 */
@Schema(name = "个人基本信息dto", description = "个人信息中的业务相关信息")
@Data
@AllArgsConstructor
public class OwnInfoDTO {

    @Schema(description = "姓名")
    private String name;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "邮箱")
    private String email;
}
