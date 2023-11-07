package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ClassName DepartmentDTO
 * @Description 部门DTO
 * @Version 1.0.0
 * @Time 2023/11/07 下午 02:58
 * @Author 卢子昂
 */
@Schema(name = "部门DTO", description = "查询部门列表dto")
@Data
@AllArgsConstructor
public class DepartmentDTO {
    @Schema(description = "部门编码")
    private String deptCode;
    @Schema(description = "部门名称")
    private String deptName;
    @Schema(description = "上级部门")
    private String superior;
    @Schema(description = "子部门")
    private List<DepartmentDTO> childList;
}
