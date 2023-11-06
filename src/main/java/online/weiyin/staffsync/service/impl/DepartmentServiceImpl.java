package online.weiyin.staffsync.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import online.weiyin.staffsync.entity.Department;
import online.weiyin.staffsync.mapper.DepartmentMapper;
import online.weiyin.staffsync.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author weiyin lu
 * @since 2023-11-06
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
