package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.RoleMenuRelevance;

/**
 * 角色-菜单关联关系 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-10
 */
@Mapper
public interface RoleMenuRelevanceMapper extends BaseMapper<RoleMenuRelevance> {

}
