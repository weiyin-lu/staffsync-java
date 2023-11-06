package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.UserInfo;

/**
 * 用户个人信息 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-06
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
