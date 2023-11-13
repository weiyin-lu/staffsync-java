package online.weiyin.staffsync.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.RoleMenuRelevance;
import online.weiyin.staffsync.request.MenuDTO;
import online.weiyin.staffsync.request.RMrelevanceDTO;
import online.weiyin.staffsync.request.RPrelevanceDTO;
import online.weiyin.staffsync.request.RelevanceBatchDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.MenuService;
import online.weiyin.staffsync.service.RoleMenuRelevanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.mybatisflex.core.query.QueryMethods.distinct;
import static online.weiyin.staffsync.entity.table.MenuTableDef.MENU;
import static online.weiyin.staffsync.entity.table.RoleMenuRelevanceTableDef.ROLE_MENU_RELEVANCE;
import static online.weiyin.staffsync.entity.table.RoleTableDef.ROLE;

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
    @Autowired
    MenuService menuService;

    @Operation(summary = "[admin]查看角色拥有的菜单列表", description = "根据角色id查询角色当前拥有的菜单")
    @ApiResponse(responseCode = "data", description = "角色列表")
    @SaCheckPermission("admin.relevance.menu.show")
    @GetMapping("/getMenuList/{roleId}")
    public Result getMenuListByRoleId(
            @Parameter(description = "角色id")
            @PathVariable String roleId) {
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create()
                .select(distinct(MENU.MENU_ID, MENU.URL, MENU.COMPONENT_PATH, MENU.DESCRIPTION))
                .from(MENU).as("m")
                .join(ROLE_MENU_RELEVANCE).as("rm")
                .on(ROLE_MENU_RELEVANCE.MENU_ID.eq(MENU.MENU_ID))
                .join(ROLE).as("r")
                .on(ROLE.ROLE_ID.eq(ROLE_MENU_RELEVANCE.ROLE_ID))
                .where(ROLE.ROLE_ID.eq(roleId));
//        执行
        List<MenuDTO> list = menuService.listAs(wrapper, MenuDTO.class);
        return Result.success("查询成功", list);
    }

    @Operation(summary = "[admin]查看所有菜单列表", description = "查看所有有效的菜单信息")
    @ApiResponse(responseCode = "data", description = "菜单列表")
    @SaCheckPermission("admin.relevance.menu.show")
    @GetMapping("/getMenuList")
    public Result getMenuListAll() {
        QueryWrapper wrapper = QueryWrapper.create()
                .select(MENU.MENU_ID, MENU.URL, MENU.COMPONENT_PATH, MENU.DESCRIPTION);
        List<MenuDTO> list = menuService.listAs(wrapper, MenuDTO.class);
        return Result.success("查询成功", list);
    }

    @Operation(summary = "[admin]为角色添加菜单（批量）", description = "根据角色id和菜单id为角色批量添加菜单")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.relevance.menu.add")
    @PostMapping("/addMenu")
    public Result addMenuForRole(@RequestBody RelevanceBatchDTO dto) {
        //        根据dto构造批量插入所用对象
        List<RoleMenuRelevance> batch = new ArrayList<>();
        for (String permissionId : dto.getList()) {
            batch.add(new RoleMenuRelevance(null, dto.getRoleId(), permissionId, "0"));
        }
//        执行
        roleMenuRelevanceService.saveBatch(batch);
        return Result.success("添加成功");
    }

    @Operation(summary = "[admin]为角色移除菜单", description = "根据角色id和菜单id为其移除权限")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.relevance.menu.remove")
    @PutMapping("/removeMenu")
    public Result removePermissionForRole(@RequestBody RMrelevanceDTO dto) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(ROLE_MENU_RELEVANCE.ROLE_ID.eq(dto.getRoleId()))
                .and(ROLE_MENU_RELEVANCE.MENU_ID.eq(dto.getMenuId()));
        boolean remove = roleMenuRelevanceService.remove(wrapper);
        if (remove) {
            return Result.success("移除成功");
        } else {
            return Result.failed(Code.REMOVE_ERROR);
        }
    }
}
