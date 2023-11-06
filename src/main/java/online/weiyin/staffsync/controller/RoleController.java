package online.weiyin.staffsync.controller;

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
import online.weiyin.staffsync.request.KeyValueDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static online.weiyin.staffsync.entity.table.RoleTableDef.ROLE;

/**
 * @ClassName RoleController
 * @Description 授权模块-角色管理
 * @Version 1.0.0
 * @Time 2023/11/06 下午 10:17
 * @Author 卢子昂
 */
@Tag(name = "授权模块-角色管理")
@RestController
@SaCheckLogin
@RequestMapping("/grant/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Operation(summary = "添加角色", description = "添加一个新角色（key：角色id；value：角色描述）")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("admin.role.add")
    )
    @PostMapping("/addRole")
    public Result addRole(@RequestBody KeyValueDTO dto) {
//        构造对象
        Role role = new Role(null, dto.getKey(), dto.getValue(), "0");
//        执行
        roleService.save(role);
        return Result.success("添加成功");
    }

    @Operation(summary = "删除角色", description = "根据角色唯一id删除一个角色")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("admin.role.remove")
    )
    @GetMapping("/removeRole/{roleId}")
    public Result removeRole(
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

    @Operation(summary = "为用户添加角色", description = "根据用户id和角色id为其添加角色（key：用户id；value：角色id）")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("role.add")
    )
    @PostMapping("/addRoleForUser")
    public Result addRoleForUser(@RequestBody KeyValueDTO dto) {
        return Result.failed(Code.NOT_FOUND);
    }

    @Operation(summary = "为用户移除角色", description = "根据用户id和角色id为其移除角色（key：用户id；value：角色id）")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckOr(
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("role.remove")
    )
    @PostMapping("/addRoleForUser")
    public Result removeRoleForUser(@RequestBody KeyValueDTO dto) {
        return Result.failed(Code.NOT_FOUND);
    }
}
