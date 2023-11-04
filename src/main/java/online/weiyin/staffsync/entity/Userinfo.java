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
 * 用户个人信息 实体类。
 *
 * @author weiyin lu
 * @since 2023-11-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "ss_userinfo")
public class Userinfo implements Serializable {

    /**
     * 数据主键
     */
    @Id(keyType = KeyType.Auto)
    private Integer rowKey;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 删除标识
     */
    @Column(isLogicDelete = true)
    private String isDelete;

}
