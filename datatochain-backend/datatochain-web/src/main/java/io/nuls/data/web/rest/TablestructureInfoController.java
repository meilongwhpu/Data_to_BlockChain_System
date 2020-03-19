
package io.nuls.data.web.rest;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.TablestructureInfoAddDTO;
import io.nuls.data.pojo.dto.TablestructureInfoUpdateDTO;
import io.nuls.data.pojo.mapper.TablestructureInfoMapper;
import io.nuls.data.pojo.po.TablestructureInfoPO;
import io.nuls.data.pojo.qo.TablestructureInfoQO;
import io.nuls.data.pojo.vo.TablestructureInfoListVO;
import io.nuls.data.pojo.vo.TablestructureInfoShowVO;
import io.nuls.data.service.TablestructureInfoService;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.TablestructureInfoAPI;
import io.nuls.data.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * 【表结构】控制器
 *
 * @author Long
 * @date 2020/03/16
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/tablestructureInfo")
public class TablestructureInfoController extends AbstractController implements TablestructureInfoAPI {

    @Autowired
    private TablestructureInfoService tablestructureInfoService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TablestructureInfoShowVO> save(@Valid @RequestBody TablestructureInfoAddDTO tablestructureInfoAddDTO) throws Exception {
        TablestructureInfoPO tablestructureInfo = tablestructureInfoService.save(tablestructureInfoAddDTO);
        return ResponseEntity.created(new URI(WebConst.API_PATH + "/tablestructureInfo/" + tablestructureInfo.getId()))
            .body(TablestructureInfoMapper.INSTANCE.toShowVO(tablestructureInfo));
    }

    @Override
    @PutMapping
    public ResponseEntity<TablestructureInfoShowVO> update(@Valid @RequestBody TablestructureInfoUpdateDTO tablestructureInfoUpdateDTO) {
        TablestructureInfoPO tablestructureInfo = tablestructureInfoService.update(tablestructureInfoUpdateDTO);
        return ResponseEntity.ok(TablestructureInfoMapper.INSTANCE.toShowVO(tablestructureInfo));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<TablestructureInfoListVO>> list(@Valid TablestructureInfoQO tablestructureInfoQO) {
        PageVO<TablestructureInfoListVO> page = tablestructureInfoService.list(tablestructureInfoQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/options")
    public ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo) {
        List<OptionVO<String, String>> options = tablestructureInfoService.findOptions(qo);
        return ResponseEntity.ok(options);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TablestructureInfoShowVO> show(@PathVariable String id) {
        TablestructureInfoShowVO tablestructureInfoShowVO = tablestructureInfoService.show(id);
        return ResponseEntity.ok(tablestructureInfoShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = tablestructureInfoService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if(ArrayUtils.isEmpty(id)){
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = tablestructureInfoService.delete(id);
        return ResponseEntity.ok(count);
    }

}


