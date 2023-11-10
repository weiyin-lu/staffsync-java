package online.weiyin.staffsync.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import online.weiyin.staffsync.entity.Menu;
import online.weiyin.staffsync.mapper.MenuMapper;
import online.weiyin.staffsync.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单信息表 服务层实现。
 *
 * @author weiyin lu
 * @since 2023-11-10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
