package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.RolePermissionRelevance;

/**
 * 角色-权限关联关系 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Mapper
public interface RolePermissionRelevanceMapper extends BaseMapper<RolePermissionRelevance> {

}
