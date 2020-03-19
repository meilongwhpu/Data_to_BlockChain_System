package io.nuls.data.web.api;

import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.SystemDictAddDTO;
import io.nuls.data.pojo.dto.SystemDictUpdateDTO;
import io.nuls.data.pojo.qo.SystemDictQO;
import io.nuls.data.pojo.vo.SystemDictListVO;
import io.nuls.data.pojo.vo.SystemDictShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * 【系统参数】API
 * <p>swagger接口文档
 *
 * @author Long
 * @date 2020/03/16
 */
@Api(tags = "【系统参数】API")
public interface SystemDictAPI {

    /**
     * 新增【系统参数】
     */
    @ApiOperation(value="新增【系统参数】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "systemDictAddDTO", dataType = "SystemDictAddDTO", value = "新增【系统参数】参数", paramType = "body"),
    })
    ResponseEntity<SystemDictShowVO> save(SystemDictAddDTO systemDictAddDTO) throws Exception;

    /**
     * 修改【系统参数】
     */
    @ApiOperation(value="修改【系统参数】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "systemDictUpdateDTO", dataType = "SystemDictUpdateDTO", value = "修改【系统参数】参数", paramType = "body"),
    })
    ResponseEntity<SystemDictShowVO> update(SystemDictUpdateDTO systemDictUpdateDTO);

    /**
     * 分页查询【系统参数】
     */
    @ApiOperation(value="分页查询【系统参数】")
    ResponseEntity<PageVO<SystemDictListVO>> list(SystemDictQO systemDictQO);

    /**
     * 查看【系统参数】详情
     */
    @ApiOperation(value="查看【系统参数】详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【系统参数】id", paramType = "path"),
    })
    ResponseEntity<SystemDictShowVO> show(String id);

    /**
     * 删除单个【系统参数】
     */
    @ApiOperation(value="删除单个【系统参数】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【系统参数】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【系统参数】
     */
    @ApiOperation(value = "批量删除【系统参数】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

}

