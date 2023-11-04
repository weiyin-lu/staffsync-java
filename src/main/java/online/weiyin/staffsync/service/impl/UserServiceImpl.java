package online.weiyin.staffsync.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import online.weiyin.staffsync.entity.User;
import online.weiyin.staffsync.mapper.UserMapper;
import online.weiyin.staffsync.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息表 服务层实现。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
