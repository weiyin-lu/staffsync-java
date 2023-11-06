package online.weiyin.staffsync.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AuthController
 * @Description 鉴权接口
 * @Version 1.0.0
 * @Time 2023/11/05 下午 07:42
 * @Author 卢子昂
 */
@Tag(name = "鉴权模块-权限信息获取")
@RestController
@SaCheckLogin
@RequestMapping("/authorize/information")
public class AuthController {

    @Operation(summary = "获取用户权限信息", description = "获取当前登录用户的权限列表")
    @GetMapping("/permissionList")
    public Result getPermissionList() {
        List<String> permissionList = StpUtil.getPermissionList();
        return Result.success(permissionList);
    }

    @Operation(summary = "获取用户角色信息", description = "获取当前登录用户的角色列表")
    @GetMapping("/roleList")
    public Result getRoleList() {
        List<String> roleList = StpUtil.getRoleList();
        return Result.success(roleList);
    }

    @Operation(summary = "检查登录状态", description = "检查当前会话是否登录")
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

    @Operation(summary = "超级管理员登录", description = "开发环境使用，直接登录超级管理员账号")
    @SaIgnore
    @GetMapping("/admin")
    public Result admin() {
        StpUtil.login("admin");
        return Result.success(StpUtil.getTokenInfo());
    }
}
