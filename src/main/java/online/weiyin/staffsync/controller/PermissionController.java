package online.weiyin.staffsync.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import online.weiyin.staffsync.entity.Permission;
import online.weiyin.staffsync.service.PermissionService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 * 权限信息表 控制层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 添加权限信息表。
     *
     * @param permission 权限信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Permission permission) {
        return permissionService.save(permission);
    }

    /**
     * 根据主键删除权限信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return permissionService.removeById(id);
    }

    /**
     * 根据主键更新权限信息表。
     *
     * @param permission 权限信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Permission permission) {
        return permissionService.updateById(permission);
    }

    /**
     * 查询所有权限信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Permission> list() {
        return permissionService.list();
    }

    /**
     * 根据权限信息表主键获取详细信息。
     *
     * @param id 权限信息表主键
     * @return 权限信息表详情
     */
    @GetMapping("getInfo/{id}")
    public Permission getInfo(@PathVariable Serializable id) {
        return permissionService.getById(id);
    }

    /**
     * 分页查询权限信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Permission> page(Page<Permission> page) {
        return permissionService.page(page);
    }

}
