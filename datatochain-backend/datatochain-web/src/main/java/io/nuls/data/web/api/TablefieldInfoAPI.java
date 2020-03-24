package io.nuls.data.web.api;

import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.TablefieldInfoAddDTO;
import io.nuls.data.pojo.dto.TablefieldInfoUpdateDTO;
import io.nuls.data.pojo.qo.TablefieldInfoQO;
import io.nuls.data.pojo.vo.TablefieldInfoListVO;
import io.nuls.data.pojo.vo.TablefieldInfoShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * 【表字段】API
 * <p>swagger接口文档
 *
 * @author Long
 * @date 2020/03/16
 */
@Api(tags = "【表字段】API")
public interface TablefieldInfoAPI {

    /**
     * 新增【表字段】
     */
    @ApiOperation(value="新增【表字段】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "tablefieldInfoAddDTO", dataType = "TablefieldInfoAddDTO", value = "新增【表字段】参数", paramType = "body"),
    })
    ResponseEntity<TablefieldInfoShowVO> save(TablefieldInfoAddDTO tablefieldInfoAddDTO) throws Exception;

    /**
     * 修改【表字段】
     */
    @ApiOperation(value="修改【表字段】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "tablefieldInfoUpdateDTO", dataType = "TablefieldInfoUpdateDTO", value = "修改【表字段】参数", paramType = "body"),
    })
    ResponseEntity<TablefieldInfoShowVO> update(TablefieldInfoUpdateDTO tablefieldInfoUpdateDTO);

    /**
     * 分页查询【表字段】
     */
    @ApiOperation(value="分页查询【表字段】")
    ResponseEntity<PageVO<TablefieldInfoListVO>> list(TablefieldInfoQO tablefieldInfoQO);

    /**
     * 查看【表字段】详情
     */
    @ApiOperation(value="查看【表字段】详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "int", value = "【表字段】id", paramType = "path"),
    })
    ResponseEntity<TablefieldInfoShowVO> show(Integer id);

    /**
     * 删除单个【表字段】
     */
    @ApiOperation(value="删除单个【表字段】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "int", value = "【表字段】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(Integer id);

    /**
     * 批量删除【表字段】
     */
    @ApiOperation(value = "批量删除【表字段】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "int", value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(Integer[] id);

}

