
package io.nuls.data.web.rest;

import com.alibaba.fastjson.JSON;
import io.nuls.data.constant.SystemDictKey;
import io.nuls.data.pojo.dto.TableDataAddDTO;
import io.nuls.data.pojo.qo.TableDataQO;
import io.nuls.data.pojo.vo.TableDataListVO;
import io.nuls.data.pojo.vo.TableInfoVO;
import io.nuls.data.pojo.vo.VerifyDataVO;
import io.nuls.data.service.TableDataManagerService;
import io.nuls.data.service.TablestructureInfoService;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.TableDataAPI;
import io.nuls.data.web.constant.WebConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private TablestructureInfoService tablestructureInfoService;

    @GetMapping(value = "/getTableInfo/{tableId}")
    public ResponseEntity<TableInfoVO> getTableInfo(@PathVariable Integer tableId){
        TableInfoVO tableInfoVO= tablestructureInfoService.getTableInfo(tableId);
        return ResponseEntity.ok(tableInfoVO);
    }

    @GetMapping
    public ResponseEntity<TableDataListVO> list(@Valid TableDataQO tableDataQO) {
        List<Map<String,Object>> tableData=tableDataManagerService.queryTableData(tableDataQO.getTableId());
        List<Map<String, String>> tableHeader=tableDataManagerService.queryTableHeader(tableDataQO.getTableId());
        Map<String, String> tableHeaderMap = new HashMap<String, String>();
/*        tableHeaderMap.put("label","操作");
        tableHeaderMap.put("prop","option");
        tableHeader.add(tableHeaderMap);
        for(Map<String,Object> data:tableData){
            data.put("option",data.get("hash"));
        }*/
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
        Iterator<Map<String,String>> iterator=tableHeader.iterator();
        while (iterator.hasNext()) {
            Map<String,String> map=iterator.next();
            if(map.get("prop").equals(SystemDictKey.INNER_HASH_FIELD)){
                iterator.remove();
                break;
            }
        }
        TableDataListVO tableDataListVO = new TableDataListVO();
        tableDataListVO.setTableHeader(tableHeader);
        return ResponseEntity.ok(tableDataListVO);
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TableDataListVO> save(@Valid @RequestBody String json) throws Exception {
        Map<String,Object> maps = (Map<String,Object>) JSON.parse(json);
        TableDataAddDTO  tableDataAddDTO= new TableDataAddDTO();
        for (Map.Entry<String,Object>  entry : maps.entrySet()) {
            if("tableId".equals(entry.getKey())){
                if(entry.getValue() instanceof String){
                    tableDataAddDTO.setTableId(Integer.valueOf((String)entry.getValue()));
                }else{
                    tableDataAddDTO.setTableId((Integer)entry.getValue());
                }
                maps.remove(entry.getKey());
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

    @GetMapping(value = "/verifyData/{tableId}/{innerId}")
    public ResponseEntity<VerifyDataVO> verifyData(@PathVariable int tableId,@PathVariable String innerId) {
        VerifyDataVO verifyDataVO=tableDataManagerService.queryTableDataByInnerid(tableId,innerId);
        return ResponseEntity.ok(verifyDataVO);
    }

    @DeleteMapping(value = "/deleteData/{tableId}")
    public ResponseEntity<Integer> deleteBatch(@PathVariable Integer tableId,@RequestBody String[] innerIds) {
        int count = tableDataManagerService.deleteData(tableId,innerIds);
        return ResponseEntity.ok(count);
    }
}


