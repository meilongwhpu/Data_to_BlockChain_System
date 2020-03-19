package io.nuls.data.web.api;

import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.qo.OperateLogQO;
import io.nuls.data.pojo.vo.OperateLogListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * 【操作日志】API
 * <p>swagger接口文档
 *
 * @author Long
 * @date 2020/03/16
 */
@Api(tags = "【操作日志】API")
public interface OperateLogAPI {

    /**
     * 分页查询【操作日志】
     */
    @ApiOperation(value="分页查询【操作日志】")
    ResponseEntity<PageVO<OperateLogListVO>> list(OperateLogQO operateLogQO);

}

