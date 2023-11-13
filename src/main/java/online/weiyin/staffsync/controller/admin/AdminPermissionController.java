package online.weiyin.staffsync.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.Permission;
import online.weiyin.staffsync.request.PermissionDTO;
import online.weiyin.staffsync.request.RoleDTO;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static online.weiyin.staffsync.entity.table.PermissionTableDef.PERMISSION;
import static online.weiyin.staffsync.entity.table.RoleTableDef.ROLE;

/**
 * @ClassName AdminPermissionController
 * @Description 系统配置管理模块-权限信息配置
 * @Version 1.0.0
 * @Time 2023/11/06 下午 10:54
 * @Author 卢子昂
 */
@Tag(name = "系统配置管理模块-权限信息配置")
@RestController
@SaCheckLogin
@RequestMapping("/admins/permissions")
public class AdminPermissionController {
    @Autowired
    private PermissionService permissionService;

    @Operation(summary = "[admin]查询所有权限列表",description = "分页查询全部权限信息，一页十条")
    @ApiResponse(responseCode = "data", description = "分页信息和权限列表数据")
    @SaCheckPermission("admin.permission.show")
    @GetMapping("/getPermissionList/{page}")
    public Result getPermissionListByPage(@PathVariable Integer page) {
        Page<Permission> pageInstance = new Page<>(page,10);
        Page<Permission> permissionPage = permissionService.page(pageInstance);
        return Result.success("查询成功",permissionPage);
    }

    @Operation(summary = "[admin]根据条件模糊查找权限", description = "分页查询条件匹配的权限，一页10条")
    @ApiResponse(responseCode = "data", description = "符合查找条件的数据")
    @Parameters({
            @Parameter(name = "permissionId", description = "权限id"),
            @Parameter(name = "permiisionName", description = "权限含义"),
            @Parameter(name = "page", description = "页码，默认为1"),
    })
    @SaCheckPermission("admin.permission.show")
    @PostMapping("/getPermissionList")
    public Result getPermissionListByCondition(@RequestBody HashMap<String, String> condition) {
        Page<PermissionDTO> page = new Page(Integer.parseInt(condition.get("page")), 10);
        QueryWrapper wrapper = QueryWrapper.create()
                .from(PERMISSION)
                .where(PERMISSION.PERMISSION_ID.like(condition.get("permissionId"), If::notNull))
                .and(PERMISSION.PERMISSION_NAME.like(condition.get("permissionName"), If::notNull));
        Page<PermissionDTO> list = permissionService.pageAs(page, wrapper, PermissionDTO.class);
        return Result.success("查询成功", list);
    }

    @Operation(summary = "[admin]添加权限", description = "添加一个新的权限")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.permission.add")
    @PostMapping("/addPermission")
    public Result addPermission(@RequestBody PermissionDTO dto) {
//        构造对象
        Permission permission = new Permission(null, dto.getPermissionId(), dto.getPermissionId(), "0");
//        执行
        permissionService.save(permission);
        return Result.success("添加成功");
    }

}
