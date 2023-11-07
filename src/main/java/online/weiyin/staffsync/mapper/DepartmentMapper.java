package online.weiyin.staffsync.mapper;

import online.weiyin.staffsync.request.DepartmentTreeDTO;
import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.Department;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-06
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("select dept_code,dept_name,superior,null from ss_department where find_in_set(dept_code," +
            "get_dept_list(#{deptCode}))")
    List<DepartmentTreeDTO> getDeptList(@RequestParam("deptCode") String deptCode);
}
