
package io.nuls.data.web.rest;

import com.sun.xml.internal.ws.api.pipe.ContentType;
import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.common.util.JsonUtil;
import io.nuls.data.pojo.dto.TableDataAddDTO;
import io.nuls.data.pojo.dto.TablespaceInfoAddDTO;
import io.nuls.data.pojo.dto.TablestructureInfoAddDTO;
import io.nuls.data.pojo.dto.TablestructureInfoUpdateDTO;
import io.nuls.data.pojo.mapper.TablespaceInfoMapper;
import io.nuls.data.pojo.mapper.TablestructureInfoMapper;
import io.nuls.data.pojo.po.TableDataPO;
import io.nuls.data.pojo.po.TableHeaderListPO;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.po.TablestructureInfoPO;
import io.nuls.data.pojo.qo.TablestructureInfoQO;
import io.nuls.data.pojo.vo.*;
import io.nuls.data.service.CreateTableManagerService;
import io.nuls.data.service.TableDataManagerService;
import io.nuls.data.service.TablestructureInfoService;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.TableDataAPI;
import io.nuls.data.web.api.TablestructureInfoAPI;
import io.nuls.data.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;


/**
 * 【表结构】控制器
 *
 * @author Long
 * @date 2020/03/16
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/tableData")
public class TableDataController extends AbstractController implements TableDataAPI {

    @Autowired
    private TableDataManagerService tableDataManagerService;


    @GetMapping
    public ResponseEntity<TableDataListVO> list(@Valid TableDataPO tableDataPO) {
        List<Map<String,Object>> tableData=tableDataManagerService.queryTableData(tableDataPO.getId());
        List<Map<String, String>> tableHeader=tableDataManagerService.queryTableHeader(tableDataPO.getId());
        TableDataListVO tableDataListVO = new TableDataListVO();
        tableDataListVO.setTableData(tableData);
        tableDataListVO.setTableHeader(tableHeader);
        return ResponseEntity.ok(tableDataListVO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TableDataListVO> show(@PathVariable Integer id) {
        List<Map<String,Object>> tableData=tableDataManagerService.queryTableData(id);
        List<Map<String, String>> tableHeader=tableDataManagerService.queryTableHeader(id);
        TableDataListVO tableDataListVO = new TableDataListVO();
        tableDataListVO.setTableData(tableData);
        tableDataListVO.setTableHeader(tableHeader);
        return ResponseEntity.ok(tableDataListVO);
    }

    @GetMapping(value = "/getTableHeader/{id}")
    public ResponseEntity<TableDataListVO> getTableHeader(@PathVariable Integer id) {
        List<Map<String, String>> tableHeader=tableDataManagerService.queryTableHeader(id);
        TableDataListVO tableDataListVO = new TableDataListVO();
        tableDataListVO.setTableHeader(tableHeader);
        return ResponseEntity.ok(tableDataListVO);
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TableDataListVO> save(@Valid @RequestBody String json) throws Exception {
        Map<String,String> maps = (Map<String,String>) JSON.parse(json);
        TableDataAddDTO  tableDataAddDTO= new TableDataAddDTO();
        for (Map.Entry<String,String>  entry : maps.entrySet()) {
            if("tableId".equals(entry.getKey())){
                tableDataAddDTO.setTableId(Integer.valueOf(entry.getValue()));
                break;
            }
        }
        tableDataAddDTO.setDataValues(maps);
        tableDataManagerService.addTableValue(tableDataAddDTO);
        return this.show(tableDataAddDTO.getTableId());
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        int count = 1;
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody Integer[] id) {
        int count = 1;
        return ResponseEntity.ok(count);
    }

}


