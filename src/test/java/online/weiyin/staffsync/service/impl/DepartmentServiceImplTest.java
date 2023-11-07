package online.weiyin.staffsync.service.impl;

import cn.hutool.json.JSONUtil;
import online.weiyin.staffsync.request.DepartmentTreeDTO;
import online.weiyin.staffsync.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName DepartmentServiceImplTest
 * @Description TODO
 * @Version 1.0.0
 * @Time 2023/11/07 下午 03:30
 * @Author 卢子昂
 */
@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    void getDeptList() {
        List<DepartmentTreeDTO> zcb = departmentService.getDeptList("ZCB");
        System.out.println(JSONUtil.toJsonPrettyStr(zcb));
    }
}