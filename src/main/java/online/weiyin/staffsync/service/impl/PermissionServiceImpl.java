package online.weiyin.staffsync.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import online.weiyin.staffsync.entity.Permission;
import online.weiyin.staffsync.mapper.PermissionMapper;
import online.weiyin.staffsync.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限信息表 服务层实现。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
