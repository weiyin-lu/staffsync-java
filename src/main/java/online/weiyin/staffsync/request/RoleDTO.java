package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName RoleDTO
 * @Description 角色数据dto
 * @Version 1.0.0
 * @Time 2023/11/07 上午 09:45
 * @Author 卢子昂
 */
@Schema(name = "角色数据dto",description = "传输角色相关信息")
@Data
public class RoleDTO {
    @Schema(description = "角色id")
    private String roleId;
    @Schema(description = "角色描述")
    private String roleName;
}
