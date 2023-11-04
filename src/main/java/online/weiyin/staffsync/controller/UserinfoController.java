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
import online.weiyin.staffsync.entity.Userinfo;
import online.weiyin.staffsync.service.UserinfoService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 * 用户个人信息 控制层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;

    /**
     * 添加用户个人信息。
     *
     * @param userinfo 用户个人信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Userinfo userinfo) {
        return userinfoService.save(userinfo);
    }

    /**
     * 根据主键删除用户个人信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return userinfoService.removeById(id);
    }

    /**
     * 根据主键更新用户个人信息。
     *
     * @param userinfo 用户个人信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Userinfo userinfo) {
        return userinfoService.updateById(userinfo);
    }

    /**
     * 查询所有用户个人信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Userinfo> list() {
        return userinfoService.list();
    }

    /**
     * 根据用户个人信息主键获取详细信息。
     *
     * @param id 用户个人信息主键
     * @return 用户个人信息详情
     */
    @GetMapping("getInfo/{id}")
    public Userinfo getInfo(@PathVariable Serializable id) {
        return userinfoService.getById(id);
    }

    /**
     * 分页查询用户个人信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Userinfo> page(Page<Userinfo> page) {
        return userinfoService.page(page);
    }

}
