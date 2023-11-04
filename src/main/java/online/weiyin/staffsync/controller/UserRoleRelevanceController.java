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
import online.weiyin.staffsync.entity.UserRoleRelevance;
import online.weiyin.staffsync.service.UserRoleRelevanceService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 * 用户-角色关联关系 控制层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@RestController
@RequestMapping("/userRoleRelevance")
public class UserRoleRelevanceController {

    @Autowired
    private UserRoleRelevanceService userRoleRelevanceService;

    /**
     * 添加用户-角色关联关系。
     *
     * @param userRoleRelevance 用户-角色关联关系
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody UserRoleRelevance userRoleRelevance) {
        return userRoleRelevanceService.save(userRoleRelevance);
    }

    /**
     * 根据主键删除用户-角色关联关系。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return userRoleRelevanceService.removeById(id);
    }

    /**
     * 根据主键更新用户-角色关联关系。
     *
     * @param userRoleRelevance 用户-角色关联关系
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody UserRoleRelevance userRoleRelevance) {
        return userRoleRelevanceService.updateById(userRoleRelevance);
    }

    /**
     * 查询所有用户-角色关联关系。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<UserRoleRelevance> list() {
        return userRoleRelevanceService.list();
    }

    /**
     * 根据用户-角色关联关系主键获取详细信息。
     *
     * @param id 用户-角色关联关系主键
     * @return 用户-角色关联关系详情
     */
    @GetMapping("getInfo/{id}")
    public UserRoleRelevance getInfo(@PathVariable Serializable id) {
        return userRoleRelevanceService.getById(id);
    }

    /**
     * 分页查询用户-角色关联关系。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<UserRoleRelevance> page(Page<UserRoleRelevance> page) {
        return userRoleRelevanceService.page(page);
    }

}
