package online.weiyin.staffsync.controller.info;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.weiyin.staffsync.entity.UserInfo;
import online.weiyin.staffsync.request.OwnInfoDTO;
import online.weiyin.staffsync.response.Code;
import online.weiyin.staffsync.response.Result;
import online.weiyin.staffsync.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static online.weiyin.staffsync.entity.table.UserInfoTableDef.USER_INFO;

/**
 * @ClassName InfoController
 * @Description 基本信息模块
 * @Version 1.0.0
 * @Time 2023/11/06 下午 09:50
 * @Author 卢子昂
 */
@Tag(name = "基本信息模块")
@RestController
@SaCheckLogin
@RequestMapping("/infomation")
public class InfoController {
    @Autowired
    UserInfoService userInfoService;

    @Operation(summary = "修改基本信息", description = "修改当前账户的基本信息")
    @ApiResponse(responseCode = "data",description = "更新后的用户信息集合")
    @SaCheckPermission("info.update")
    @PutMapping("/setInfo")
    public Result setInfo(@RequestBody OwnInfoDTO info) {
//        构造插入列表
        UserInfo of = UpdateEntity.of(UserInfo.class);
        of.setName(info.getName());
        of.setEmail(info.getEmail());
        of.setPhone(info.getPhone());
//        构造查询条件
        QueryWrapper wrapper = QueryWrapper.create().where(USER_INFO.USER_ID.eq(StpUtil.getLoginId()));
//        执行
        boolean update = userInfoService.update(of, wrapper);
        if (update) {
            UserInfo one = userInfoService.getOne(wrapper);
            return Result.success("更新信息成功", one);
        } else {
            return Result.failed(Code.UPDATE_ERROR);
        }
    }
}
