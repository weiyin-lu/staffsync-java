package online.weiyin.staffsync.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.User;
import online.weiyin.staffsync.entity.UserInfo;
import online.weiyin.staffsync.request.Authorize;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.UserInfoService;
import online.weiyin.staffsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static online.weiyin.staffsync.entity.table.UserTableDef.USER;

/**
 * @ClassName SignController
 * @Description 登录注册接口
 * @Version 1.0.0
 * @Time 2023/11/06 下午 01:54
 * @Author 卢子昂
 */
@Tag(name = "鉴权模块-用户认证相关")
@RestController
@RequestMapping("/sign")
public class SignController {

    @Autowired
    UserService userService;
    @Autowired
    UserInfoService userinfoService;

    @Operation(summary = "登录", description = "根据账号密码登录系统")
    @PostMapping("/login")
    public Result login(@RequestBody Authorize info) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(USER.USER_ID.eq(info.getUsername()))
                .and(USER.PASSWORD.eq(SecureUtil.md5(info.getPassword())));
        User one = userService.getOne(wrapper);
        if (one != null) {
            StpUtil.login(info.getUsername());
            return Result.success("登录成功", StpUtil.getTokenInfo());
        } else {
            return Result.failed("登录失败，账号或密码错误", Code.AUTHORIZE_ERROR);
        }
    }

    @Operation(summary = "登出", description = "登出当前用户")
    @GetMapping("/logout")
    public Result logout() {
        StpUtil.logout();
        return Result.success("已注销");
    }

    @Operation(summary = "注册", description = "根据账号密码注册一个用户")
    @PostMapping("/register")
    @Transactional
    public Result register(Authorize reg) {
//        构造对象
        User user = new User(null, reg.getUsername(), SecureUtil.md5(reg.getPassword()), "0");
        UserInfo userInfo = new UserInfo(null, reg.getUsername(), null, null, "");
//        在用户表中插入登录的基本信息
        userService.save(user);
//        在个人信息表中插入一条基本的占位记录
        userinfoService.save(userInfo);
        return Result.success("注册成功");
    }
}
