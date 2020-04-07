package io.nuls.data.web.api;

import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.TablespaceInfoAddDTO;
import io.nuls.data.pojo.dto.TablespaceInfoUpdateDTO;
import io.nuls.data.pojo.po.TableDataPO;
import io.nuls.data.pojo.qo.TablespaceInfoQO;
import io.nuls.data.pojo.vo.TableDataListVO;
import io.nuls.data.pojo.vo.TablespaceInfoListVO;
import io.nuls.data.pojo.vo.TablespaceInfoShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
    ResponseEntity<TableDataListVO> list(TableDataPO tableDataPO);


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
        @ApiImplicitParam(name = "id", dataType = "int", value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(Integer[] id);

}

