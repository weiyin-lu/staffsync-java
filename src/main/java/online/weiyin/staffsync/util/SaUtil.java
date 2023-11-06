package online.weiyin.staffsync.util;

import cn.dev33.satoken.stp.StpInterface;
import com.mybatisflex.core.query.QueryWrapper;
import online.weiyin.staffsync.service.PermissionService;
import online.weiyin.staffsync.service.RoleService;
import online.weiyin.staffsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mybatisflex.core.query.QueryMethods.distinct;
import static online.weiyin.staffsync.entity.table.PermissionTableDef.PERMISSION;
import static online.weiyin.staffsync.entity.table.RolePermissionRelevanceTableDef.ROLE_PERMISSION_RELEVANCE;
import static online.weiyin.staffsync.entity.table.RoleTableDef.ROLE;
import static online.weiyin.staffsync.entity.table.UserRoleRelevanceTableDef.USER_ROLE_RELEVANCE;
import static online.weiyin.staffsync.entity.table.UserTableDef.USER;

/**
 * @ClassName SaUtil
 * @Description 权限和身份信息获取接口
 * @Version 1.0.0
 * @Time 2023/11/06 上午 10:59
 * @Author 卢子昂
 */
@Component
public class SaUtil implements StpInterface {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
//        构造查询条件，会同时检查用户、角色、权限、用户-角色关联、角色-权限关联的有效性
        QueryWrapper wrapper = QueryWrapper.create()
                .select(distinct(PERMISSION.PERMISSION_ID))
                .from(PERMISSION.as("p"))
                .leftJoin(ROLE_PERMISSION_RELEVANCE).as("rp")
                .on(PERMISSION.PERMISSION_ID.eq(ROLE_PERMISSION_RELEVANCE.PERMISSION_ID))
                .leftJoin(ROLE).as("r")
                .on(ROLE.ROLE_ID.eq(ROLE_PERMISSION_RELEVANCE.ROLE_ID))
                .leftJoin(USER_ROLE_RELEVANCE)
                .on(USER_ROLE_RELEVANCE.USER_ID.eq(ROLE.ROLE_ID))
                .leftJoin(USER)
                .on(USER.USER_ID.eq(USER_ROLE_RELEVANCE.USER_ID))
                .where(USER.USER_ID.eq(o));
        List<String> permissionList = permissionService.listAs(wrapper, String.class);
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
//        构造查询条件，会同时检查用户、角色、用户-角色关联三者的有效性（是否有被删除的记录）
        QueryWrapper wrapper = QueryWrapper.create()
                .select(ROLE.ROLE_ID)
                .from(ROLE.as("r"))
                .leftJoin(USER_ROLE_RELEVANCE).as("ur")
                .on(ROLE.ROLE_ID.eq(USER_ROLE_RELEVANCE.ROLE_ID))
                .leftJoin(USER).as("u")
                .on(USER.USER_ID.eq(USER_ROLE_RELEVANCE.USER_ID))
                .where(USER.USER_ID.eq(o));
        List<String> roleList = roleService.listAs(wrapper, String.class);
        return roleList;
    }
}
