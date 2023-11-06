package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName KeyValueDTO
 * @Description 键值对DTO
 * @Version 1.0.0
 * @Time 2023/11/06 下午 10:38
 * @Author 卢子昂
 */
@Schema(name = "通用键值对DTO", description = "类键值对形式请求参数统一dto")
@Data
@AllArgsConstructor
public class KeyValueDTO {

    @Schema(description = "用途根据使用场景变更")
    private String key;
    @Schema(description = "用途根据使用场景变更")
    private String value;
}
