package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.UserRoleRelevance;

/**
 * 用户-角色关联关系 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Mapper
public interface UserRoleRelevanceMapper extends BaseMapper<UserRoleRelevance> {

}
