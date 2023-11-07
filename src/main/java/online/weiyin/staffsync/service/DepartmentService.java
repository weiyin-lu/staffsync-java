package online.weiyin.staffsync.service;

import com.mybatisflex.core.service.IService;
import online.weiyin.staffsync.entity.Department;
import online.weiyin.staffsync.request.DepartmentDTO;

import java.util.List;

/**
 *  服务层。
 *
 * @author weiyin lu
 * @since 2023-11-06
 */
public interface DepartmentService extends IService<Department> {

    List<DepartmentDTO> getDeptList(String deptCode);
}
