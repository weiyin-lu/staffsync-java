package online.weiyin.staffsync.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import online.weiyin.staffsync.entity.Menu;

/**
 * 菜单信息表 映射层。
 *
 * @author weiyin lu
 * @since 2023-11-10
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
