package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName RMrelevanceDTO
 * @Description 角色-菜单关联dto
 * @Version 1.0.0
 * @Time 2023/11/10 下午 04:29
 * @Author 卢子昂
 */
@Schema(name = "角色-菜单关联dto", description = "角色和菜单的映射关系")
@Data
public class RMrelevanceDTO {
    @Schema(description = "角色id")
    private String roleId;
    @Schema(description = "菜单id")
    private String menuId;
}
