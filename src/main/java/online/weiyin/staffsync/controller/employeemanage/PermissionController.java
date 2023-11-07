package online.weiyin.staffsync.controller.employeemanage;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckOr;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.Permission;
import online.weiyin.staffsync.entity.RolePermissionRelevance;
import online.weiyin.staffsync.entity.UserRoleRelevance;
import online.weiyin.staffsync.request.PermissionDTO;
import online.weiyin.staffsync.request.RPrelevanceDTO;
import online.weiyin.staffsync.request.URrelevanceDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.PermissionService;
import online.weiyin.staffsync.service.RolePermissionRelevanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static online.weiyin.staffsync.entity.table.RolePermissionRelevanceTableDef.ROLE_PERMISSION_RELEVANCE;
import static online.weiyin.staffsync.entity.table.UserRoleRelevanceTableDef.USER_ROLE_RELEVANCE;

/**
 * @ClassName PermissionController
 * @Description 人员管理模块-权限管理
 * @Version 1.0.0
 * @Time 2023/11/06 下午 10:54
 * @Author 卢子昂
 */
@Tag(name = "人员管理模块-权限管理")
@RestController
@SaCheckLogin
@RequestMapping("/grant/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    RolePermissionRelevanceService rolePermissionRelevanceService;

    @Operation(summary = "添加权限", description = "添加一个新的权限")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckRole("admin")
    @PostMapping("/addPermission")
    public Result addPermission(@RequestBody PermissionDTO dto) {
//        构造对象
        Permission permission = new Permission(null, dto.getPermissionId(), dto.getPermissionId(), "0");
//        执行
        permissionService.save(permission);
        return Result.success("添加成功");
    }

    @Operation(summary = "为角色添加权限", description = "根据角色id和权限id为角色添加权限")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            permission = @SaCheckPermission("permission.add"),
            role = @SaCheckRole("admin")
    )
    @PostMapping("/addPermissionForRole")
    public Result addPermissionForRole(@RequestBody RPrelevanceDTO dto) {
        RolePermissionRelevance relevance = new RolePermissionRelevance(null, dto.getRoleId(), dto.getPermissionId(), "0");
        rolePermissionRelevanceService.save(relevance);
        return Result.success("添加成功");
    }

    @Operation(summary = "为角色移除权限", description = "根据角色id和权限id为其移除权限")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            permission = @SaCheckPermission("permission.remove"),
            role = @SaCheckRole("admin")
    )
    @PutMapping("/removePermissionForRole")
    public Result removePermissionForRole(@RequestBody RPrelevanceDTO dto) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(ROLE_PERMISSION_RELEVANCE.ROLE_ID.eq(dto.getRoleId()))
                .and(ROLE_PERMISSION_RELEVANCE.PERMISSION_ID.eq(dto.getPermissionId()));
        boolean remove = rolePermissionRelevanceService.remove(wrapper);
        if(remove) {
            return Result.success("移除成功");
        } else {
            return Result.failed(Code.REMOVE_ERROR);
        }
    }

}
