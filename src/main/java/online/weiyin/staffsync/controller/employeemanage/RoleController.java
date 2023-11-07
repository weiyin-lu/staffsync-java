package online.weiyin.staffsync.controller.employeemanage;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckOr;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.Role;
import online.weiyin.staffsync.entity.UserRoleRelevance;
import online.weiyin.staffsync.request.RoleDTO;
import online.weiyin.staffsync.request.URrelevanceDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.RoleService;
import online.weiyin.staffsync.service.UserRoleRelevanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static online.weiyin.staffsync.entity.table.RoleTableDef.ROLE;
import static online.weiyin.staffsync.entity.table.UserRoleRelevanceTableDef.USER_ROLE_RELEVANCE;

/**
 * @ClassName RoleController
 * @Description 人员管理模块-角色管理
 * @Version 1.0.0
 * @Time 2023/11/06 下午 10:17
 * @Author 卢子昂
 */
@Tag(name = "人员管理模块-角色管理")
@RestController
@SaCheckLogin
@RequestMapping("/grant/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleRelevanceService userRoleRelevanceService;

    @Operation(summary = "添加角色", description = "添加一个新角色")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            permission = @SaCheckPermission("admin.role.add"),
            role = @SaCheckRole("admin")
    )
    @PostMapping("/addRole")
    public Result addRole(@RequestBody RoleDTO dto) {
//        构造对象
        Role role = new Role(null, dto.getRoleId(), dto.getRoleName(), "0");
//        执行
        roleService.save(role);
        return Result.success("添加成功");
    }

    @Operation(summary = "删除角色", description = "根据角色唯一id删除一个角色")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            permission = @SaCheckPermission("admin.role.remove"),
            role = @SaCheckRole("admin")
    )
    @DeleteMapping("/removeRole/{roleId}")
    public Result removeRoleByRoleId(
            @Parameter(description = "角色id")
            @PathVariable String roleId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(ROLE.ROLE_ID.eq(roleId));
        boolean remove = roleService.remove(wrapper);
        if (remove) {
            return Result.success("删除成功");
        } else {
            return Result.failed(Code.REMOVE_ERROR);
        }
    }

    @Operation(summary = "为用户添加角色", description = "根据用户id和角色id为其添加角色")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            permission = @SaCheckPermission("role.add"),
            role = @SaCheckRole("admin")
    )
    @PostMapping("/addRoleForUser")
    public Result addRoleForUser(@RequestBody URrelevanceDTO dto) {
        UserRoleRelevance relevance = new UserRoleRelevance(null, dto.getUserId(), dto.getRoleId(), "0");
        userRoleRelevanceService.save(relevance);
        return Result.success("添加成功");
    }

    @Operation(summary = "为用户移除角色", description = "根据用户id和角色id为其移除角色")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            permission = @SaCheckPermission("role.remove"),
            role = @SaCheckRole("admin")
    )
    @PutMapping("/removeRoleForUser")
    public Result removeRoleForUser(@RequestBody URrelevanceDTO dto) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(USER_ROLE_RELEVANCE.USER_ID.eq(dto.getUserId()))
                .and(USER_ROLE_RELEVANCE.ROLE_ID.eq(dto.getRoleId()));
        boolean remove = userRoleRelevanceService.remove(wrapper);
        if(remove) {
            return Result.success("移除成功");
        } else {
            return Result.failed(Code.REMOVE_ERROR);
        }
    }
}
