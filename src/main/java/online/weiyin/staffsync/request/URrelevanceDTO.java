package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName URrelevanceDTO
 * @Description 用户-角色关联dto
 * @Version 1.0.0
 * @Time 2023/11/07 上午 09:51
 * @Author 卢子昂
 */
@Schema(name = "用户-角色关联dto",description = "用户和角色的映射关系")
@Data
public class URrelevanceDTO {
    @Schema(description = "用户id")
    private String userId;
    @Schema(description = "角色id")
    private String roleId;
}
