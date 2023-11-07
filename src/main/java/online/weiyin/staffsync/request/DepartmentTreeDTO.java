package online.weiyin.staffsync.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import online.weiyin.staffsync.entity.Department;

import java.util.List;

/**
 * @ClassName DepartmentDTO
 * @Description 部门DTO
 * @Version 1.0.0
 * @Time 2023/11/07 下午 02:58
 * @Author 卢子昂
 */
@Schema(name = "部门DTO", description = "查询部门嵌套列表使用的实体类")
@Data
@AllArgsConstructor
public class DepartmentTreeDTO {
    @Schema(description = "部门编码")
    private String deptCode;
    @Schema(description = "部门名称")
    private String deptName;
    @Schema(description = "上级部门")
    private String superior;
    @Schema(description = "子部门")
    private List<DepartmentTreeDTO> childList;
}
