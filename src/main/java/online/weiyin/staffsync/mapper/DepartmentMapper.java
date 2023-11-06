package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.Department;

/**
 *  映射层。
 *
 * @author weiyin lu
 * @since 2023-11-06
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
