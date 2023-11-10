package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName MenuDTO
 * @Description 菜单数据dto
 * @Version 1.0.0
 * @Time 2023/11/10 下午 05:10
 * @Author 卢子昂
 */
@Schema(name = "菜单数据dto",description = "传输菜单相关信息")
@Data
public class MenuDTO {
    /**
     * 菜单id
     */
    private String menuId;

    @Schema(description = "菜单路径")
    private String url;

    @Schema(description = "组件路径")
    private String componentPath;

    @Schema(description = "菜单描述")
    private String description;
}
