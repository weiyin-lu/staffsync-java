package online.weiyin.staffsync.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.Permission;
import online.weiyin.staffsync.request.KeyValueDTO;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PermissionController
 * @Description 授权模块-权限管理
 * @Version 1.0.0
 * @Time 2023/11/06 下午 10:54
 * @Author 卢子昂
 */
@Tag(name = "授权模块-权限管理")
@RestController
@SaCheckLogin
@RequestMapping("/grant/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @Operation(summary = "添加权限", description = "添加一个新的权限（key：权限id；value：权限描述）")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckRole("admin")
    @PostMapping("/addPermission")
    public Result addRole(@RequestBody KeyValueDTO dto) {
//        构造对象
        Permission permission = new Permission(null, dto.getKey(), dto.getValue(), "0");
//        执行
        permissionService.save(permission);
        return Result.success("添加成功");
    }

}