package online.weiyin.staffsync.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AuthController
 * @Description 用户认证接口
 * @Version 1.0.0
 * @Time 2023/11/05 下午 07:42
 * @Author 卢子昂
 */
@Api(value = "用户认证",tags = "鉴权相关")
@RestController
@SaCheckLogin
@RequestMapping("/auth")
public class AuthController {

    @ApiOperation(value = "返回权限列表",notes = "给出当前登录用户的所有权限")
    @GetMapping("/permissionList")
    public Result getPermissionList() {
        List<String> permissionList = StpUtil.getPermissionList();
        return Result.success(permissionList);
    }

    @ApiOperation(value = "返回角色列表",notes = "给出当前登录用户的所有角色")
    @GetMapping("/roleList")
    public Result getRoleList() {
        List<String> roleList = StpUtil.getRoleList();
        return Result.success(roleList);
    }

    @ApiOperation(value = "登录检查",notes = "检查用户的登录状态（认证豁免）")
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

    @ApiOperation(value = "超级管理员登录",notes = "直接登录超级管理员，仅限开发环境")
    @SaIgnore
    @GetMapping("/admin")
    public Result admin() {
        StpUtil.login("admin");
        return Result.success(StpUtil.getTokenInfo());
    }
}
