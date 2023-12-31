package online.weiyin.staffsync.controller.authorize;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.request.MenuDTO;
import online.weiyin.staffsync.request.PermissionDTO;
import online.weiyin.staffsync.request.RoleDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.MenuService;
import online.weiyin.staffsync.service.PermissionService;
import online.weiyin.staffsync.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mybatisflex.core.query.QueryMethods.distinct;
import static online.weiyin.staffsync.entity.table.MenuTableDef.MENU;
import static online.weiyin.staffsync.entity.table.PermissionTableDef.PERMISSION;
import static online.weiyin.staffsync.entity.table.RoleMenuRelevanceTableDef.ROLE_MENU_RELEVANCE;
import static online.weiyin.staffsync.entity.table.RoleTableDef.ROLE;
import static online.weiyin.staffsync.entity.table.UserRoleRelevanceTableDef.USER_ROLE_RELEVANCE;
import static online.weiyin.staffsync.entity.table.UserTableDef.USER;

/**
 * @ClassName AuthController
 * @Description 认证模块-认证信息获取
 * @Version 1.0.0
 * @Time 2023/11/05 下午 07:42
 * @Author 卢子昂
 */
@Tag(name = "认证模块-认证信息获取")
@RestController
@SaCheckLogin
@RequestMapping("/authorizes/informations")
public class AuthController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @Operation(summary = "获取用户权限信息", description = "获取指定用户的权限列表（权限和权限描述）")
    @ApiResponse(responseCode = "data", description = "权限列表")
    @GetMapping("/getPermissionList/{userId}")
    public Result getPermissionListByUserId(
            @Parameter(description = "用户id")
            @PathVariable String userId) {
//        构造查询条件，根据鉴权时获取的列表获得对应的名称
        QueryWrapper wrapper = QueryWrapper.create()
                .select(PERMISSION.PERMISSION_ID, PERMISSION.PERMISSION_NAME)
                .from(PERMISSION)
                .where(PERMISSION.PERMISSION_ID.in(StpUtil.getPermissionList(userId)));
//        执行
        List<PermissionDTO> permissionList = permissionService.listAs(wrapper, PermissionDTO.class);
        return Result.success(permissionList);
    }

    @Operation(summary = "获取用户角色信息", description = "获取指定用户的角色列表（角色和角色描述）")
    @ApiResponse(responseCode = "data", description = "角色列表")
    @GetMapping("/getroleList/{userId}")
    public Result getRoleListByUserId(
            @Parameter(description = "用户id")
            @PathVariable String userId) {
//        构造查询条件，从鉴权时获取的角色列表获得对应的名称
        QueryWrapper wrapper = QueryWrapper.create()
                .select(ROLE.ROLE_ID, ROLE.ROLE_NAME)
                .from(ROLE)
                .where(ROLE.ROLE_ID.in(StpUtil.getRoleList(userId)));
//        执行
        List<RoleDTO> roleList = roleService.listAs(wrapper, RoleDTO.class);
        return Result.success(roleList);
    }

    @Operation(summary = "检查登录状态", description = "检查当前会话是否登录")
    @ApiResponse(responseCode = "data", description = "无")
    @SaIgnore
    @GetMapping("/isLogin")
    public Result isLogin() {
        boolean login = StpUtil.isLogin();
        if (login) {
            return Result.success("登录状态正常");
        } else {
            return Result.failed("未登录", Code.AUTHORIZE_ERROR);
        }
    }

    @Operation(summary = "获取菜单列表", description = "获取当前用户可用的菜单信息")
    @ApiResponse(responseCode = "data", description = "菜单信息列表")
    @GetMapping("/getMenuList/{userId}")
    public Result getMenuListByUserId(
            @Parameter(description = "登录用户id")
            @PathVariable String userId) {
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create()
                .select(distinct(MENU.MENU_ID, MENU.URL, MENU.COMPONENT_PATH, MENU.DESCRIPTION))
                .from(MENU.as("m"))
                .join(ROLE_MENU_RELEVANCE).as("rm")
                .on(ROLE_MENU_RELEVANCE.MENU_ID.eq(MENU.MENU_ID))
                .join(ROLE).as("r")
                .on(ROLE.ROLE_ID.eq(ROLE_MENU_RELEVANCE.ROLE_ID))
                .join(USER_ROLE_RELEVANCE).as("ur")
                .on(USER_ROLE_RELEVANCE.ROLE_ID.eq(ROLE.ROLE_ID))
                .join(USER)
                .on(USER.USER_ID.eq(USER_ROLE_RELEVANCE.USER_ID))
                .where(USER.USER_ID.eq(userId));
//        执行
        List<MenuDTO> dtoList = menuService.listAs(wrapper, MenuDTO.class);
        return Result.success("查询成功",dtoList);
    }
}
