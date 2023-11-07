package online.weiyin.staffsync.controller.employeemanage;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckOr;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.request.DepartmentDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName DeptController
 * @Description 人员管理模块-部门管理
 * @Version 1.0.0
 * @Time 2023/11/07 下午 01:53
 * @Author 卢子昂
 */
@Tag(name = "人员管理模块-部门管理")
@RestController
@SaCheckLogin
@RequestMapping("/department")
public class DeptController {
    @Autowired
    DepartmentService departmentService;

    @Operation(summary = "查询部门列表", description = "以嵌套数组的形式获取部门列表")
    @ApiResponse(responseCode = "data", description = "部门列表")
    @GetMapping("/deptList/{deptCode}")
    public Result getDeptList(
            @Parameter(description = "查询的起始部门编码")
            @PathVariable String deptCode) {
        List<DepartmentDTO> deptList = departmentService.getDeptList(deptCode);
        return Result.success("查询成功",deptList);
    }
}
