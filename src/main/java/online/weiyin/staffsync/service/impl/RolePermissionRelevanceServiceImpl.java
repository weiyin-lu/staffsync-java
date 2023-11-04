package online.weiyin.staffsync.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import online.weiyin.staffsync.entity.RolePermissionRelevance;
import online.weiyin.staffsync.mapper.RolePermissionRelevanceMapper;
import online.weiyin.staffsync.service.RolePermissionRelevanceService;
import org.springframework.stereotype.Service;

/**
 * 角色-权限关联关系 服务层实现。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Service
public class RolePermissionRelevanceServiceImpl extends ServiceImpl<RolePermissionRelevanceMapper, RolePermissionRelevance> implements RolePermissionRelevanceService {

}
