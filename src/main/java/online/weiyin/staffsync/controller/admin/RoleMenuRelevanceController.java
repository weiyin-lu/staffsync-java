package online.weiyin.staffsync.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.RoleMenuRelevance;
import online.weiyin.staffsync.request.RMrelevanceDTO;
import online.weiyin.staffsync.request.RPrelevanceDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.RoleMenuRelevanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static online.weiyin.staffsync.entity.table.RoleMenuRelevanceTableDef.ROLE_MENU_RELEVANCE;

/**
 * @ClassName RoleMenuRelevanceController
 * @Description 系统配置管理模块-角色菜单关系配置
 * @Version 1.0.0
 * @Time 2023/11/10 下午 04:22
 * @Author 卢子昂
 */
@Tag(name = "系统配置管理模块-角色菜单关系配置")
@RestController
@SaCheckLogin
@RequestMapping("/admins/relevances/menu")
public class RoleMenuRelevanceController {
    @Autowired
    RoleMenuRelevanceService roleMenuRelevanceService;

    @Operation(summary = "[admin]为角色添加菜单", description = "根据角色id和菜单id为角色添加菜单")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.relevance.menu.add")
    @PostMapping("/addMenuForRole")
    public Result addMenuForRole(@RequestBody RMrelevanceDTO dto) {
        RoleMenuRelevance relevance = new RoleMenuRelevance(null, dto.getRoleId(), dto.getMenuId(), "0");
        roleMenuRelevanceService.save(relevance);
        return Result.success("添加成功");
    }

    @Operation(summary = "[admin]为角色移除菜单", description = "根据角色id和菜单id为其移除权限")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.relevance.menu.remove")
    @PutMapping("/removeMenuForRole")
    public Result removePermissionForRole(@RequestBody RPrelevanceDTO dto) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(ROLE_MENU_RELEVANCE.ROLE_ID.eq(dto.getRoleId()))
                .and(ROLE_MENU_RELEVANCE.MENU_ID.eq(dto.getPermissionId()));
        boolean remove = roleMenuRelevanceService.remove(wrapper);
        if (remove) {
            return Result.success("移除成功");
        } else {
            return Result.failed(Code.REMOVE_ERROR);
        }
    }
}
