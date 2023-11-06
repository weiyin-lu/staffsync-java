package online.weiyin.staffsync.util;

import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName SaUtilTest
 * @Description 权限和身份测试
 * @Version 1.0.0
 * @Time 2023/11/06 上午 11:22
 * @Author 卢子昂
 */
@SpringBootTest
class SaUtilTest {

    @Test
    void getPermissionList() {
        StpUtil.login("admin");
        List<String> permissionList = StpUtil.getPermissionList();
        System.out.println(permissionList);
//        ‘*’代表一切权限
        System.out.println(StpUtil.hasPermission("any"));
    }

    @Test
    void getRoleList() {
        StpUtil.login("admin");
        List<String> roleList = StpUtil.getRoleList();
        System.out.println(roleList);
//        '*'代表一切角色
        System.out.println(StpUtil.hasRole("any"));
    }
}