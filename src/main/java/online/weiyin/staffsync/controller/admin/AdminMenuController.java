package online.weiyin.staffsync.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.Menu;
import online.weiyin.staffsync.request.MenuDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static online.weiyin.staffsync.entity.table.MenuTableDef.MENU;

/**
 * @ClassName AdminMenuController
 * @Description 系统配置管理模块-菜单信息配置
 * @Version 1.0.0
 * @Time 2023/11/10 下午 04:22
 * @Author 卢子昂
 */
@Tag(name = "系统配置管理模块-菜单信息配置")
@RestController
@SaCheckLogin
@RequestMapping("/admins/menus")
public class AdminMenuController {

    @Autowired
    MenuService menuService;

    @Operation(summary = "[admin]查询所有菜单列表", description = "分页查询全部菜单信息，一页10条")
    @ApiResponse(responseCode = "data", description = "分页信息和菜单列表数据")
    @SaCheckPermission("admin.menu.show")
    @GetMapping("/getMenuList/{page}")
    public Result getMenuListByPage(
            @Parameter(description = "页码")
            @PathVariable Integer page) {
        Page<Menu> pageInstance = new Page<>(page, 10);
        Page<Menu> menuPage = menuService.page(pageInstance);
        return Result.success("查询成功", menuPage);
    }

    @Operation(summary = "[admin]配置新菜单", description = "配置一个新菜单的信息")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.menu.add")
    @PostMapping("/addRole")
    public Result addRole(@RequestBody MenuDTO dto) {
//        构造对象
        Menu menu = new Menu(null, dto.getMenuId(), dto.getUrl(), dto.getComponentPath(),
                dto.getDescription(), "0");
//        执行
        menuService.save(menu);
        return Result.success("添加成功");
    }

    @Operation(summary = "[admin]删除菜单", description = "根据菜单唯一id移除一个菜单")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.menu.remove")
    @DeleteMapping("/removeMenu/{menuId}")
    public Result removeMenuByMenuId(
            @Parameter(description = "菜单id")
            @PathVariable String menuId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(MENU.MENU_ID.eq(menuId));
        boolean remove = menuService.remove(wrapper);
        if (remove) {
            return Result.success("删除成功");
        } else {
            return Result.failed(Code.REMOVE_ERROR);
        }
    }

    @Operation(summary = "[admin]修改菜单", description = "根据菜单唯一id修改一个菜单的信息")
    @ApiResponse(responseCode = "data", description = "无")
    @SaCheckPermission("admin.menu.remove")
    @PutMapping("/setMenu")
    public Result setRoleByRoleId(@RequestBody MenuDTO menuDTO) {
//        构造更新字段
        Menu of = UpdateEntity.of(Menu.class);
        of.setMenuId(menuDTO.getMenuId());
        of.setUrl(menuDTO.getUrl());
        of.setComponentPath(menuDTO.getComponentPath());
        of.setDescription(menuDTO.getDescription());
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create().where(MENU.MENU_ID.eq(menuDTO.getMenuId()));

        boolean update = menuService.update(of, wrapper);
        if (update) {
            return Result.success("修改成功");
        } else {
            return Result.failed(Code.UPDATE_ERROR);
        }
    }
}
