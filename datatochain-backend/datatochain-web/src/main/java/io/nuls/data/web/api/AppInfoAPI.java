package io.nuls.data.web.api;

import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.AppInfoAddDTO;
import io.nuls.data.pojo.dto.AppInfoUpdateDTO;
import io.nuls.data.pojo.qo.AppInfoQO;
import io.nuls.data.pojo.vo.AppInfoListVO;
import io.nuls.data.pojo.vo.AppInfoShowVO;
import io.nuls.data.pojo.vo.TablespaceInfoListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 【应用】API
 * <p>swagger接口文档
 *
 * @author Long
 * @date 2020/03/16
 */
@Api(tags = "【应用】API")
public interface AppInfoAPI {

    /**
     * 新增【应用】
     */
    @ApiOperation(value="新增【应用】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "appInfoAddDTO", dataType = "AppInfoAddDTO", value = "新增【应用】参数", paramType = "body"),
    })
    ResponseEntity<AppInfoShowVO> save(AppInfoAddDTO appInfoAddDTO) throws Exception;

    /**
     * 修改【应用】
     */
    @ApiOperation(value="修改【应用】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "appInfoUpdateDTO", dataType = "AppInfoUpdateDTO", value = "修改【应用】参数", paramType = "body"),
    })
    ResponseEntity<AppInfoShowVO> update(AppInfoUpdateDTO appInfoUpdateDTO);

    /**
     * 分页查询【应用】
     */
    @ApiOperation(value="分页查询【应用】")
    ResponseEntity<PageVO<AppInfoListVO>> list(AppInfoQO appInfoQO);

    /**
     * 查询【应用】选项列表
     */
    @ApiOperation(value = "查询【应用】选项列表")
    ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo);

    /**
     * 查看【应用】详情
     */
    @ApiOperation(value="查看【应用】详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【应用】id", paramType = "path"),
    })
    ResponseEntity<AppInfoShowVO> show(String id);

    /**
     * 删除单个【应用】
     */
    @ApiOperation(value="删除单个【应用】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【应用】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【应用】
     */
    @ApiOperation(value = "批量删除【应用】")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

    /**
     * 获取【表空间】关联
     */
    @ApiOperation(value="获取【表空间】关联")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【应用】id", paramType = "path"),
    })
    ResponseEntity<List<TablespaceInfoListVO>> fetchTablespaceInfoList(String id);

    /**
     * 添加【表空间】关联
     */
    @ApiOperation(value="添加【表空间】关联")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【应用】id", paramType = "path"),
        @ApiImplicitParam(name = "tablespaceId", dataType = "String", value = "【表空间】id数组", paramType = "body"),
    })
    ResponseEntity<Integer> addTablespaceInfo(String id,String[] tablespaceId);

    /**
     * 移除【表空间】关联
     */
    @ApiOperation(value="移除【表空间】关联")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", dataType = "String", value = "【应用】id", paramType = "path"),
        @ApiImplicitParam(name = "tablespaceId", dataType = "String", value = "【表空间】id数组", paramType = "body"),
    })
    ResponseEntity<Integer> removeTablespaceInfo(String id,String[] tablespaceId);

}

