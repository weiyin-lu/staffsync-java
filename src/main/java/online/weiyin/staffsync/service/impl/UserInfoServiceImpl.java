package online.weiyin.staffsync.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import online.weiyin.staffsync.entity.UserInfo;
import online.weiyin.staffsync.mapper.UserInfoMapper;
import online.weiyin.staffsync.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户个人信息 服务层实现。
 *
 * @author weiyin lu
 * @since 2023-11-06
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
