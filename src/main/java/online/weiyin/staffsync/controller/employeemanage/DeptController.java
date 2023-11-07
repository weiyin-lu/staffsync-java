package online.weiyin.staffsync.controller.employeemanage;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckOr;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.Department;
import online.weiyin.staffsync.request.DepartmentDTO;
import online.weiyin.staffsync.request.DepartmentTreeDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static online.weiyin.staffsync.entity.table.DepartmentTableDef.DEPARTMENT;

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
    @GetMapping("/getDeptList/{deptCode}")
    public Result getDeptList(
            @Parameter(description = "查询的起始部门编码")
            @PathVariable String deptCode) {
        List<DepartmentTreeDTO> deptList = departmentService.getDeptList(deptCode);
        return Result.success("查询成功", deptList);
    }

    @Operation(summary = "查找部门名称", description = "根据部门id返回一条部门信息")
    @ApiResponse(responseCode = "data", description = "部门信息")
    @GetMapping("/getDept/{deptCode}")
    public Result getDeptByCode(
            @Parameter(description = "部门编码")
            @PathVariable String deptCode) {
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create()
                .where(DEPARTMENT.DEPT_CODE.eq(deptCode));
//        执行
        Department dept = departmentService.getOne(wrapper);
        return Result.success("查询成功", dept);
    }

    @Operation(summary = "添加部门", description = "添加一个新部门")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            permission = @SaCheckPermission("admin.dept.add"),
            role = @SaCheckRole("admin")
    )
    @PostMapping("/addDept")
    public Result addDept(@RequestBody DepartmentDTO dto) {
        Department dept = new Department(null, dto.getDeptCode(), dto.getDeptName(), dto.getSuperior(), "0");
        departmentService.save(dept);
        return Result.success("添加成功");
    }

    @Operation(summary = "修改部门", description = "根据部门编码修改部门")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            permission = @SaCheckPermission("admin.dept.update"),
            role = @SaCheckRole("admin")
    )
    @PutMapping("/setDept")
    public Result setDeptByCode(@RequestBody DepartmentDTO dto) {
//        构造更新对象
        Department of = UpdateEntity.of(Department.class);
        of.setDeptName(dto.getDeptName());
        of.setSuperior(dto.getSuperior());
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create()
                .where(DEPARTMENT.DEPT_CODE.eq(dto.getDeptCode()));
//        执行
        boolean update = departmentService.update(of, wrapper);
        if (update) {
            return Result.success("更新成功");
        } else {
            return Result.failed(Code.UPDATE_ERROR);
        }
    }

    public Result removeDept() {
        return Result.failed();
    }
}
