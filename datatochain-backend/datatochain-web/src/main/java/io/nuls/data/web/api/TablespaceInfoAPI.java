package io.nuls.data.web.api;

import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.TablespaceInfoAddDTO;
import io.nuls.data.pojo.dto.TablespaceInfoUpdateDTO;
import io.nuls.data.pojo.qo.TablespaceInfoQO;
import io.nuls.data.pojo.vo.TablespaceInfoListVO;
import io.nuls.data.pojo.vo.TablespaceInfoShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 【表空间】API
 * <p>swagger接口文档
 *
 * @author Long
 * @date 2020/03/16
 */
@Api(tags = "【表空间】API")
public interface TablespaceInfoAPI {

    /**
     * 新增【表空间】
     */
    @ApiOperation(value="新增【表空间】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "tablespaceInfoAddDTO", dataType = "TablespaceInfoAddDTO", value = "新增【表空间】参数", paramType = "body"),
    })
    ResponseEntity<TablespaceInfoShowVO> save(TablespaceInfoAddDTO tablespaceInfoAddDTO) throws Exception;

    /**
     * 修改【表空间】
     */
    @ApiOperation(value="修改【表空间】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "tablespaceInfoUpdateDTO", dataType = "TablespaceInfoUpdateDTO", value = "修改【表空间】参数", paramType = "body"),
    })
    ResponseEntity<TablespaceInfoShowVO> update(TablespaceInfoUpdateDTO tablespaceInfoUpdateDTO);

    /**
     * 分页查询【表空间】
     */
    @ApiOperation(value="分页查询【表空间】")
    ResponseEntity<PageVO<TablespaceInfoListVO>> list(TablespaceInfoQO tablespaceInfoQO);

    /**
     * 查询【表空间】选项列表
     */
    @ApiOperation(value = "查询【表空间】选项列表")
    ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo);

    /**
     * 查看【表空间】详情
     */
    @ApiOperation(value="查看【表空间】详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【表空间】id", paramType = "path"),
    })
    ResponseEntity<TablespaceInfoShowVO> show(String id);

    /**
     * 删除单个【表空间】
     */
    @ApiOperation(value="删除单个【表空间】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【表空间】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【表空间】
     */
    @ApiOperation(value = "批量删除【表空间】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

}

