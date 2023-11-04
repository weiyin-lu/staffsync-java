package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.User;

/**
 * 用户信息表 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
