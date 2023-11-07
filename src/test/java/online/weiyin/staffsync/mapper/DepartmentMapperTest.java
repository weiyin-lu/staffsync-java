package online.weiyin.staffsync.mapper;

import online.weiyin.staffsync.request.DepartmentTreeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName DepartmentMapperTest
 * @Description TODO
 * @Version 1.0.0
 * @Time 2023/11/07 下午 03:19
 * @Author 卢子昂
 */
@SpringBootTest
class DepartmentMapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    void getDeptList() {
        List<DepartmentTreeDTO> zcb = departmentMapper.getDeptList("ZCB");
        System.out.println(zcb);
    }
}