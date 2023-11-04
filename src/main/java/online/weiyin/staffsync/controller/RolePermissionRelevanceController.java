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
import online.weiyin.staffsync.entity.RolePermissionRelevance;
import online.weiyin.staffsync.service.RolePermissionRelevanceService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 * 角色-权限关联关系 控制层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@RestController
@RequestMapping("/rolePermissionRelevance")
public class RolePermissionRelevanceController {

    @Autowired
    private RolePermissionRelevanceService rolePermissionRelevanceService;

    /**
     * 添加角色-权限关联关系。
     *
     * @param rolePermissionRelevance 角色-权限关联关系
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody RolePermissionRelevance rolePermissionRelevance) {
        return rolePermissionRelevanceService.save(rolePermissionRelevance);
    }

    /**
     * 根据主键删除角色-权限关联关系。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return rolePermissionRelevanceService.removeById(id);
    }

    /**
     * 根据主键更新角色-权限关联关系。
     *
     * @param rolePermissionRelevance 角色-权限关联关系
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody RolePermissionRelevance rolePermissionRelevance) {
        return rolePermissionRelevanceService.updateById(rolePermissionRelevance);
    }

    /**
     * 查询所有角色-权限关联关系。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<RolePermissionRelevance> list() {
        return rolePermissionRelevanceService.list();
    }

    /**
     * 根据角色-权限关联关系主键获取详细信息。
     *
     * @param id 角色-权限关联关系主键
     * @return 角色-权限关联关系详情
     */
    @GetMapping("getInfo/{id}")
    public RolePermissionRelevance getInfo(@PathVariable Serializable id) {
        return rolePermissionRelevanceService.getById(id);
    }

    /**
     * 分页查询角色-权限关联关系。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<RolePermissionRelevance> page(Page<RolePermissionRelevance> page) {
        return rolePermissionRelevanceService.page(page);
    }

}
