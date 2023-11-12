package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName RelevanceBatchDTO
 * @Description 批量插入dto
 * @Version 1.0.0
 * @Time 2023/11/12 下午 07:24
 * @Author 卢子昂
 */
@Schema(name = "批量插入dto", description = "批量插入用户/菜单")
@Data
public class RelevanceBatchDTO {
    @Schema(description = "角色id")
    private String roleId;
    @Schema(description = "菜单id或权限id列表")
    private List<String> list;
}
