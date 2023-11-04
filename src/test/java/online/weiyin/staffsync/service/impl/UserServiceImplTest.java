package online.weiyin.staffsync.service.impl;

import online.weiyin.staffsync.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName UserServiceImplTest
 * @Description UserServiceImplTest测试类
 * @Version 1.0.0
 * @Time 2023/11/04 下午 07:34
 * @Author 卢子昂
 */
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void list() {
        System.out.println(userService.list());
    }
}