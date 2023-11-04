package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.Permission;

/**
 * 权限信息表 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
