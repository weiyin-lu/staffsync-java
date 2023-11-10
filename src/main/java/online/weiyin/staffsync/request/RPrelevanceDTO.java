package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName RPrelevanceDTO
 * @Description 角色-权限关联dto
 * @Version 1.0.0
 * @Time 2023/11/07 下午 01:34
 * @Author 卢子昂
 */
@Schema(name = "角色-权限关联dto", description = "角色和权限的映射关系")
@Data
public class RPrelevanceDTO {
    @Schema(description = "角色id")
    private String roleId;
    @Schema(description = "权限id")
    private String permissionId;
}
