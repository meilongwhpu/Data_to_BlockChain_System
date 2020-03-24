package io.nuls.data.web.api;

import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.TablestructureInfoAddDTO;
import io.nuls.data.pojo.dto.TablestructureInfoUpdateDTO;
import io.nuls.data.pojo.qo.TablestructureInfoQO;
import io.nuls.data.pojo.vo.TablestructureInfoListVO;
import io.nuls.data.pojo.vo.TablestructureInfoShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 【表结构】API
 * <p>swagger接口文档
 *
 * @author Long
 * @date 2020/03/16
 */
@Api(tags = "【表结构】API")
public interface TablestructureInfoAPI {

    /**
     * 新增【表结构】
     */
    @ApiOperation(value="新增【表结构】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "tablestructureInfoAddDTO", dataType = "TablestructureInfoAddDTO", value = "新增【表结构】参数", paramType = "body"),
    })
    ResponseEntity<TablestructureInfoShowVO> save(TablestructureInfoAddDTO tablestructureInfoAddDTO) throws Exception;

    /**
     * 修改【表结构】
     */
    @ApiOperation(value="修改【表结构】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "tablestructureInfoUpdateDTO", dataType = "TablestructureInfoUpdateDTO", value = "修改【表结构】参数", paramType = "body"),
    })
    ResponseEntity<TablestructureInfoShowVO> update(TablestructureInfoUpdateDTO tablestructureInfoUpdateDTO);

    /**
     * 分页查询【表结构】
     */
    @ApiOperation(value="分页查询【表结构】")
    ResponseEntity<PageVO<TablestructureInfoListVO>> list(TablestructureInfoQO tablestructureInfoQO);

    /**
     * 查询【表结构】选项列表
     */
    @ApiOperation(value = "查询【表结构】选项列表")
    ResponseEntity<List<OptionVO<Integer, String>>> findOptions(OptionQO<Integer, String> qo);

    /**
     * 查看【表结构】详情
     */
    @ApiOperation(value="查看【表结构】详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "int", value = "【表结构】id", paramType = "path"),
    })
    ResponseEntity<TablestructureInfoShowVO> show(Integer id);

    /**
     * 删除单个【表结构】
     */
    @ApiOperation(value="删除单个【表结构】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "int", value = "【表结构】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(Integer id);

    /**
     * 批量删除【表结构】
     */
    @ApiOperation(value = "批量删除【表结构】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "int", value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(Integer[] id);

}

