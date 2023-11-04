package online.weiyin.staffsync.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import online.weiyin.staffsync.entity.Userinfo;
import online.weiyin.staffsync.mapper.UserinfoMapper;
import online.weiyin.staffsync.service.UserinfoService;
import org.springframework.stereotype.Service;

/**
 * 用户个人信息 服务层实现。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements UserinfoService {

}
