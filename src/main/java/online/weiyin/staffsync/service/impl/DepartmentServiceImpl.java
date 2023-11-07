package online.weiyin.staffsync.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import online.weiyin.staffsync.entity.Department;
import online.weiyin.staffsync.mapper.DepartmentMapper;
import online.weiyin.staffsync.request.DepartmentDTO;
import online.weiyin.staffsync.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 服务层实现。
 *
 * @author weiyin lu
 * @since 2023-11-06
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDTO> getDeptList(String deptCode) {
//        调用mapper
        List<DepartmentDTO> list = departmentMapper.getDeptList(deptCode);
//        启动递归
        List<DepartmentDTO> collect = list.stream()
//                过滤 从查询列表的根节点开始遍历
                .filter(a -> a.getDeptCode().equals(deptCode))
//                批量操作 为每个节点插入其子节点
                .peek(m -> m.setChildList(listToTree(m, list)))
//                转换为列表
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * 链表转树（递归）
     * @param root 父节点
     * @param list 子节点（列表）
     * @return
     */
    public static List<DepartmentDTO> listToTree(DepartmentDTO root, List<DepartmentDTO> list) {
        return list.stream()
//                过滤 当前节点下的子节点
                .filter(a -> Objects.equals(a.getSuperior(), root.getDeptCode()))
//                批量操作 为每个子节点插入其子节点
                .peek(m -> m.setChildList(listToTree(m, list)))
//                转换为列表
                .collect(Collectors.toList());
    }
}
