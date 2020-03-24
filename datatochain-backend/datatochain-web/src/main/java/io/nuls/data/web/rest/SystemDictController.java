
package io.nuls.data.web.rest;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.SystemDictAddDTO;
import io.nuls.data.pojo.dto.SystemDictUpdateDTO;
import io.nuls.data.pojo.mapper.SystemDictMapper;
import io.nuls.data.pojo.po.SystemDictPO;
import io.nuls.data.pojo.qo.SystemDictQO;
import io.nuls.data.pojo.vo.SystemDictListVO;
import io.nuls.data.pojo.vo.SystemDictShowVO;
import io.nuls.data.service.SystemDictService;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.SystemDictAPI;
import io.nuls.data.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * 【系统参数】控制器
 *
 * @author Long
 * @date 2020/03/16
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/systemDict")
public class SystemDictController extends AbstractController implements SystemDictAPI {

    @Autowired
    private SystemDictService systemDictService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SystemDictShowVO> save(@Valid @RequestBody SystemDictAddDTO systemDictAddDTO) throws Exception {
        SystemDictPO systemDict = systemDictService.save(systemDictAddDTO);
        return ResponseEntity.created(new URI(WebConst.API_PATH + "/systemDict/" + systemDict.getId()))
            .body(SystemDictMapper.INSTANCE.toShowVO(systemDict));
    }

    @Override
    @PutMapping
    public ResponseEntity<SystemDictShowVO> update(@Valid @RequestBody SystemDictUpdateDTO systemDictUpdateDTO) {
        SystemDictPO systemDict = systemDictService.update(systemDictUpdateDTO);
        return ResponseEntity.ok(SystemDictMapper.INSTANCE.toShowVO(systemDict));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<SystemDictListVO>> list(@Valid SystemDictQO systemDictQO) {
        PageVO<SystemDictListVO> page = systemDictService.list(systemDictQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SystemDictShowVO> show(@PathVariable Integer id) {
        SystemDictShowVO systemDictShowVO = systemDictService.show(id);
        return ResponseEntity.ok(systemDictShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        int count = systemDictService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody Integer[] id) {
        if(ArrayUtils.isEmpty(id)){
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = systemDictService.delete(id);
        return ResponseEntity.ok(count);
    }

}


