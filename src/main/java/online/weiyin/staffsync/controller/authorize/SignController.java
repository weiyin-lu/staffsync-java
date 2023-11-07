package online.weiyin.staffsync.controller.authorize;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.User;
import online.weiyin.staffsync.entity.UserInfo;
import online.weiyin.staffsync.request.AuthorizeDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.UserInfoService;
import online.weiyin.staffsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static online.weiyin.staffsync.entity.table.UserInfoTableDef.USER_INFO;
import static online.weiyin.staffsync.entity.table.UserTableDef.USER;

/**
 * @ClassName SignController
 * @Description 认证模块-系统访问认证
 * @Version 1.0.0
 * @Time 2023/11/06 下午 01:54
 * @Author 卢子昂
 */
@Tag(name = "认证模块-系统访问认证")
@RestController
@RequestMapping("/authorize/sign")
public class SignController {

    @Autowired
    UserService userService;
    @Autowired
    UserInfoService userinfoService;

    @Operation(summary = "登录", description = "根据账号密码登录系统")
    @ApiResponse(responseCode = "data",description = "当前账号的认证、角色、权限、个人信息")
    @PostMapping("/login")
    public Result login(@RequestBody AuthorizeDTO login) {
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create()
                .where(USER.USER_ID.eq(login.getUsername()))
                .and(USER.PASSWORD.eq(SecureUtil.md5(login.getPassword())));
//        查询是否有对应的记录
        User one = userService.getOne(wrapper);
        if (one != null) {
//            登录
            StpUtil.login(login.getUsername());
//            封装一个map，返回当前账号的登录、角色、权限、个人信息等
            UserInfo info = userinfoService.getOne(QueryWrapper.create()
                    .where(USER_INFO.USER_ID.eq(login.getUsername())));
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("token", StpUtil.getTokenInfo());
            resultMap.put("role", StpUtil.getRoleList());
            resultMap.put("permission", StpUtil.getPermissionList());
            resultMap.put("info", info);
//            返回
            return Result.success("登录成功", resultMap);
        } else {
            return Result.failed("登录失败，账号或密码错误", Code.AUTHORIZE_ERROR);
        }
    }

    @Operation(summary = "登出", description = "登出当前用户")
    @ApiResponse(responseCode = "data",description = "无")
    @GetMapping("/logout")
    public Result logout() {
        StpUtil.logout();
        return Result.success("已注销");
    }

    @Operation(summary = "注册", description = "根据账号密码注册一个用户")
    @ApiResponse(responseCode = "data",description = "无")
    @PostMapping("/register")
    @Transactional
    public Result register(@RequestBody AuthorizeDTO reg) {
//        构造对象
        User user = new User(null, reg.getUsername(), SecureUtil.md5(reg.getPassword()), "0");
        UserInfo userInfo = new UserInfo(null, reg.getUsername(), null, null, null, null, null, "0");
//        在用户表中插入登录的基本信息
        userService.save(user);
//        在个人信息表中插入一条基本的占位记录
        userinfoService.save(userInfo);
        return Result.success("注册成功");
    }
}
