package online.weiyin.staffsync.controller.admin;

import cn.hutool.json.JSONUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import online.weiyin.staffsync.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static com.mybatisflex.core.query.QueryMethods.column;
import static online.weiyin.staffsync.entity.table.DepartmentTableDef.DEPARTMENT;

/**
 * @ClassName AdminDeptControllerTest
 * @Description TODO
 * @Version 1.0.0
 * @Time 2023/11/12 上午 02:44
 * @Author 卢子昂
 */
@SpringBootTest
class AdminDeptControllerTest {
    @Autowired
    DepartmentService departmentService;
    @Test
    public void getDeptListByPageTest() {
//        分页条件
        Page<Map> pageInstance = new Page<>(1, 10);
//        查询条件
        QueryWrapper wrapper = QueryWrapper.create()
                .select(DEPARTMENT.DEFAULT_COLUMNS,column("d2.dept_name").as("superiorName"))
                .from(DEPARTMENT.as("d1"))
                .leftJoin(DEPARTMENT).as("d2")
                .on(column("d1.superior").eq(column("d2.dept_code")));
        Page<Map> departmentPage = departmentService.pageAs(pageInstance, wrapper, Map.class);
        System.out.println(JSONUtil.toJsonPrettyStr(departmentPage));

    }
}