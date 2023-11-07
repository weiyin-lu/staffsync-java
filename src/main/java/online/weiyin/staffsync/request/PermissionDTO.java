package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName PermissionDTO
 * @Description 权限数据dto
 * @Version 1.0.0
 * @Time 2023/11/07 上午 09:40
 * @Author 卢子昂
 */
@Schema(name = "权限数据dto",description = "传输权限相关信息")
@Data
public class PermissionDTO {
    @Schema(description = "权限id")
    private String permissionId;
    @Schema(description = "权限描述")
    private String permissionName;

}
