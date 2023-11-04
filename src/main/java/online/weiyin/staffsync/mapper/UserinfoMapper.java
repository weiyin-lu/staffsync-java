package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.Userinfo;

/**
 * 用户个人信息 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Mapper
public interface UserinfoMapper extends BaseMapper<Userinfo> {

}
