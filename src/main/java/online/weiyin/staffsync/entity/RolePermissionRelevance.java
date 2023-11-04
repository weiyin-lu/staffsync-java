package online.weiyin.staffsync.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色-权限关联关系 实体类。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "ss_role_permission_relevance")
public class RolePermissionRelevance implements Serializable {

    /**
     * 数据主键
     */
    @Id(keyType = KeyType.Auto)
    private Integer rowKey;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id
     */
    private String permissionId;

    /**
     * 删除标识
     */
    @Column(isLogicDelete = true)
    private String isDelete;

}
