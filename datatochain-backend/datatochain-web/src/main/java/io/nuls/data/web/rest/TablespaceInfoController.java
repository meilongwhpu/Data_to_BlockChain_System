
package io.nuls.data.web.rest;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.TablespaceInfoAddDTO;
import io.nuls.data.pojo.dto.TablespaceInfoUpdateDTO;
import io.nuls.data.pojo.mapper.TablespaceInfoMapper;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.qo.TablespaceInfoQO;
import io.nuls.data.pojo.vo.TablespaceInfoListVO;
import io.nuls.data.pojo.vo.TablespaceInfoShowVO;
import io.nuls.data.service.TablespaceInfoService;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.TablespaceInfoAPI;
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
 * 【表空间】控制器
 *
 * @author Long
 * @date 2020/03/16
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/tablespaceInfo")
public class TablespaceInfoController extends AbstractController implements TablespaceInfoAPI {

    @Autowired
    private TablespaceInfoService tablespaceInfoService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TablespaceInfoShowVO> save(@Valid @RequestBody TablespaceInfoAddDTO tablespaceInfoAddDTO) throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoService.save(tablespaceInfoAddDTO);
        return ResponseEntity.created(new URI(WebConst.API_PATH + "/tablespaceInfo/" + tablespaceInfo.getId()))
            .body(TablespaceInfoMapper.INSTANCE.toShowVO(tablespaceInfo));
    }

    @Override
    @PutMapping
    public ResponseEntity<TablespaceInfoShowVO> update(@Valid @RequestBody TablespaceInfoUpdateDTO tablespaceInfoUpdateDTO) {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoService.update(tablespaceInfoUpdateDTO);
        return ResponseEntity.ok(TablespaceInfoMapper.INSTANCE.toShowVO(tablespaceInfo));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<TablespaceInfoListVO>> list(@Valid TablespaceInfoQO tablespaceInfoQO) {
        PageVO<TablespaceInfoListVO> page = tablespaceInfoService.list(tablespaceInfoQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/options")
    public ResponseEntity<List<OptionVO<Integer, String>>> findOptions(OptionQO<Integer, String> qo) {
        List<OptionVO<Integer, String>> options = tablespaceInfoService.findOptions(qo);
        return ResponseEntity.ok(options);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TablespaceInfoShowVO> show(@PathVariable Integer id) {
        TablespaceInfoShowVO tablespaceInfoShowVO = tablespaceInfoService.show(id);
        return ResponseEntity.ok(tablespaceInfoShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        int count = tablespaceInfoService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody Integer[] id) {
        if(ArrayUtils.isEmpty(id)){
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = tablespaceInfoService.delete(id);
        return ResponseEntity.ok(count);
    }

}


