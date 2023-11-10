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
 * 菜单信息表 实体类。
 *
 * @author weiyin lu
 * @since 2023-11-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "ss_menu")
public class Menu implements Serializable {

    /**
     * 数据主键
     */
    @Id(keyType = KeyType.Auto)
    private Integer rowKey;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 组件路径
     */
    private String componentPath;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 删除标识
     */
    @Column(isLogicDelete = true)
    private String isDelete;

}
