
package io.nuls.data.web.rest;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.TablefieldInfoAddDTO;
import io.nuls.data.pojo.dto.TablefieldInfoUpdateDTO;
import io.nuls.data.pojo.mapper.TablefieldInfoMapper;
import io.nuls.data.pojo.po.TablefieldInfoPO;
import io.nuls.data.pojo.qo.TablefieldInfoQO;
import io.nuls.data.pojo.vo.TablefieldInfoListVO;
import io.nuls.data.pojo.vo.TablefieldInfoShowVO;
import io.nuls.data.service.TablefieldInfoService;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.TablefieldInfoAPI;
import io.nuls.data.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * 【表字段】控制器
 *
 * @author Long
 * @date 2020/03/16
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/tablefieldInfo")
public class TablefieldInfoController extends AbstractController implements TablefieldInfoAPI {

    @Autowired
    private TablefieldInfoService tablefieldInfoService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TablefieldInfoShowVO> save(@Valid @RequestBody TablefieldInfoAddDTO tablefieldInfoAddDTO) throws Exception {
        TablefieldInfoPO tablefieldInfo = tablefieldInfoService.save(tablefieldInfoAddDTO);
        return ResponseEntity.created(new URI(WebConst.API_PATH + "/tablefieldInfo/" + tablefieldInfo.getId()))
            .body(TablefieldInfoMapper.INSTANCE.toShowVO(tablefieldInfo));
    }

    @Override
    @PutMapping
    public ResponseEntity<TablefieldInfoShowVO> update(@Valid @RequestBody TablefieldInfoUpdateDTO tablefieldInfoUpdateDTO) {
        TablefieldInfoPO tablefieldInfo = tablefieldInfoService.update(tablefieldInfoUpdateDTO);
        return ResponseEntity.ok(TablefieldInfoMapper.INSTANCE.toShowVO(tablefieldInfo));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<TablefieldInfoListVO>> list(@Valid TablefieldInfoQO tablefieldInfoQO) {
        PageVO<TablefieldInfoListVO> page = tablefieldInfoService.list(tablefieldInfoQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TablefieldInfoShowVO> show(@PathVariable Integer id) {
        TablefieldInfoShowVO tablefieldInfoShowVO = tablefieldInfoService.show(id);
        return ResponseEntity.ok(tablefieldInfoShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        int count = tablefieldInfoService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody Integer[] id) {
        if(ArrayUtils.isEmpty(id)){
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = tablefieldInfoService.delete(id);
        return ResponseEntity.ok(count);
    }

}


