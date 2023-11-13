package online.weiyin.staffsync.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.Department;
import online.weiyin.staffsync.request.DepartmentDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.mybatisflex.core.query.QueryMethods.column;
import static com.mybatisflex.core.query.QueryMethods.count;
import static online.weiyin.staffsync.entity.table.DepartmentTableDef.DEPARTMENT;

/**
 * @ClassName AdminDeptController
 * @Description 系统配置管理模块-部门信息配置
 * @Version 1.0.0
 * @Time 2023/11/07 下午 01:53
 * @Author 卢子昂
 */
@Tag(name = "系统配置管理模块-部门信息配置")
@RestController
@SaCheckLogin
@RequestMapping("/admins/departments")
public class AdminDeptController {
    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "[admin]查询所有部门列表", description = "分页查询全部部门信息，一页10条")
    @ApiResponse(responseCode = "data", description = "分页信息和部门列表数据")
    @SaCheckPermission("admin.dept.show")
    @GetMapping("/getDeptList/{page}")
    public Result getDeptListByPage(
            @Parameter(description = "页码")
            @PathVariable Integer page) {
//        构造分页条件
        Page<Map> pageInstance = new Page<>(page, 10);
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create()
                .select(DEPARTMENT.DEPT_CODE.as("deptCode"), DEPARTMENT.DEPT_NAME.as("deptName"),
                        column("d2.dept_name").as("superiorName"))
                .from(DEPARTMENT.as("d1"))
                .leftJoin(DEPARTMENT).as("d2")
                .on(column("d1.superior").eq(column("d2.dept_code")));
        Page<Map> departmentPage = departmentService.pageAs(pageInstance, wrapper, Map.class);
        return Result.success("查询成功", departmentPage);
    }

    @Operation(summary = "[admin]根据条件模糊查找部门", description = "分页查询条件匹配的部门，一页10条")
    @ApiResponse(responseCode = "data", description = "符合查找条件的数据")
    @Parameters({
            @Parameter(name = "deptCode", description = "部门编码"),
            @Parameter(name = "deptName", description = "部门名称"),
            @Parameter(name = "deptName", description = "页码，默认为1"),
    })
    @SaCheckPermission("admin.dept.show")
    @PostMapping("/getDeptList")
    public Result getDeptListByCondition(@RequestBody HashMap<String, String> condition) {
        Page<Map> pageInstance = new Page<>(Integer.parseInt(condition.get("page")), 10);
        QueryWrapper wrapper = QueryWrapper.create()
                .select(DEPARTMENT.DEPT_CODE.as("deptCode"), DEPARTMENT.DEPT_NAME.as("deptName"),
                        column("d2.dept_name").as("superiorName"))
                .from(DEPARTMENT.as("d1"))
                .leftJoin(DEPARTMENT).as("d2")
                .on(column("d1.superior").eq(column("d2.dept_code")))
                .where(DEPARTMENT.DEPT_CODE.like(condition.get("deptCode"), If::notNull))
                .and(DEPARTMENT.DEPT_NAME.like(condition.get("deptName"), If::notNull));
        Page<Map> list = departmentService.pageAs(pageInstance, wrapper, Map.class);
        return Result.success("查询成功", list);
    }

    @Operation(summary = "[admin]添加部门", description = "添加一个新部门")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.dept.add")
    @PostMapping("/addDept")
    public Result addDept(@RequestBody DepartmentDTO dto) {
        Department dept = new Department(null, dto.getDeptCode(), dto.getDeptName(), dto.getSuperior(), "0");
        departmentService.save(dept);
        return Result.success("添加成功");
    }

    @Operation(summary = "[admin]修改部门", description = "根据部门编码修改部门")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.dept.update")
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

    @Operation(summary = "[admin]删除部门", description = "根据部门编码删除部门，被删除的部门不得有下属部门")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.dept.remove")
    @DeleteMapping("/removeDept/{deptCode}")
    public Result removeDeptByDeptCode(@PathVariable String deptCode) {
//        检查该部门是否有子部门，如有，拒绝删除
        QueryWrapper wrapper = QueryWrapper.create()
                .select(count(DEPARTMENT.SUPERIOR))
                .where(DEPARTMENT.SUPERIOR.eq(deptCode));
        Integer count = departmentService.getOneAs(wrapper, Integer.class);
        if (count > 0) {
            return Result.failed("该部门有未移去的下属部门", Code.REMOVE_ERROR);
        } else {
//            如果没有下属部门，进行删除
            QueryWrapper wrapper1 = QueryWrapper.create()
                    .where(DEPARTMENT.DEPT_CODE.eq(deptCode));
            departmentService.remove(wrapper1);
            return Result.success("删除成功");
        }
    }
}
