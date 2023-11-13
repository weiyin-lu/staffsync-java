package online.weiyin.staffsync.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.Role;
import online.weiyin.staffsync.request.RoleDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static online.weiyin.staffsync.entity.table.RoleTableDef.ROLE;

/**
 * @ClassName AdminRoleController
 * @Description 系统配置管理模块-角色信息配置
 * @Version 1.0.0
 * @Time 2023/11/06 下午 10:17
 * @Author 卢子昂
 */
@Tag(name = "系统配置管理模块-角色信息配置")
@RestController
@SaCheckLogin
@RequestMapping("/admins/roles")
public class AdminRoleController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "[admin]查询所有角色列表", description = "分页查询全部角色信息，一页10条")
    @ApiResponse(responseCode = "data", description = "分页信息和角色列表数据")
    @SaCheckPermission("admin.role.show")
    @GetMapping("/getRoleList/{page}")
    public Result getRoleListByPage(
            @Parameter(description = "页码")
            @PathVariable Integer page) {
        Page<RoleDTO> pageInstance = new Page<>(page, 10);
        QueryWrapper wrapper = QueryWrapper.create()
                .select(ROLE.ROLE_ID, ROLE.ROLE_NAME);
        Page<RoleDTO> rolePage = roleService.pageAs(pageInstance,wrapper,RoleDTO.class);
        return Result.success("查询成功", rolePage);
    }

    @Operation(summary = "[admin]添加角色", description = "添加一个新角色")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.role.add")
    @PostMapping("/addRole")
    public Result addRole(@RequestBody RoleDTO dto) {
//        构造对象
        Role role = new Role(null, dto.getRoleId(), dto.getRoleName(), "0");
//        执行
        roleService.save(role);
        return Result.success("添加成功");
    }

    @Operation(summary = "[admin]根据条件模糊查找角色", description = "分页查询条件匹配的角色，一页10条")
    @ApiResponse(responseCode = "data", description = "符合查找条件的数据")
    @Parameters({
            @Parameter(name = "roleId", description = "角色id"),
            @Parameter(name = "roleName", description = "角色含义"),
            @Parameter(name = "page", description = "页码，默认为1"),
    })
    @SaCheckPermission("admin.role.show")
    @PostMapping("/getRoleList")
    public Result getRoleListByCondition(@RequestBody HashMap<String, String> condition) {
        Page<RoleDTO> page = new Page(Integer.parseInt(condition.get("page")), 10);
        QueryWrapper wrapper = QueryWrapper.create()
                .from(ROLE)
                .where(ROLE.ROLE_ID.like(condition.get("roleId"), If::notNull))
                .and(ROLE.ROLE_NAME.like(condition.get("roleName"), If::notNull));
        Page<RoleDTO> list = roleService.pageAs(page, wrapper, RoleDTO.class);
        return Result.success("查询成功", list);
    }

    @Operation(summary = "[admin]删除角色", description = "根据角色唯一id删除一个角色")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.role.remove")
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

    @Operation(summary = "[admin]修改角色", description = "根据角色唯一id修改一个角色的名称")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.role.remove")
    @PutMapping("/setRole")
    public Result setRoleByRoleId(@RequestBody RoleDTO roleDTO) {
//        构造更新字段
        Role of = UpdateEntity.of(Role.class);
        of.setRoleName(roleDTO.getRoleName());
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create().where(ROLE.ROLE_ID.eq(roleDTO.getRoleId()));
//        执行
        boolean update = roleService.update(of, wrapper);
        if (update) {
            return Result.success("修改成功");
        } else {
            return Result.failed(Code.UPDATE_ERROR);
        }
    }
}
