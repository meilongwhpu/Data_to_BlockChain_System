package io.nuls.data.web.api;

import io.nuls.data.pojo.qo.TableDataQO;
import io.nuls.data.pojo.vo.TableDataListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * 【表数据】API
 * <p>swagger接口文档
 *
 * @author Long
 * @date 2020/03/16
 */
@Api(tags = "【表数据】API")
public interface TableDataAPI {


    /**
     * 分页查询【表数据】
     */
    @ApiOperation(value="分页查询【表数据】")
    ResponseEntity<TableDataListVO> list(TableDataQO tableDataQO);


    /**
     * 删除单个【表空间】
     */
    @ApiOperation(value="删除单个【表数据】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "int", value = "【表数据】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(Integer id);

    /**
     * 批量删除【表空间】
     */
    @ApiOperation(value = "批量删除【表数据】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "tableId", dataType = "int", value = "【表数据】tableId", paramType = "path"),
        @ApiImplicitParam(name = "hash", dataType = "String", value = "hash数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(Integer tableId,String[] hashes);

}

