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
import online.weiyin.staffsync.entity.Role;
import online.weiyin.staffsync.service.RoleService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 * 角色信息表 控制层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色信息表。
     *
     * @param role 角色信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Role role) {
        return roleService.save(role);
    }

    /**
     * 根据主键删除角色信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return roleService.removeById(id);
    }

    /**
     * 根据主键更新角色信息表。
     *
     * @param role 角色信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Role role) {
        return roleService.updateById(role);
    }

    /**
     * 查询所有角色信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Role> list() {
        return roleService.list();
    }

    /**
     * 根据角色信息表主键获取详细信息。
     *
     * @param id 角色信息表主键
     * @return 角色信息表详情
     */
    @GetMapping("getInfo/{id}")
    public Role getInfo(@PathVariable Serializable id) {
        return roleService.getById(id);
    }

    /**
     * 分页查询角色信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Role> page(Page<Role> page) {
        return roleService.page(page);
    }

}
