
package io.nuls.data.web.rest;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.dto.AppInfoAddDTO;
import io.nuls.data.pojo.dto.AppInfoUpdateDTO;
import io.nuls.data.pojo.mapper.AppInfoMapper;
import io.nuls.data.pojo.mapper.TablespaceInfoMapper;
import io.nuls.data.pojo.po.AppInfoPO;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.qo.AppInfoQO;
import io.nuls.data.pojo.vo.AppInfoListVO;
import io.nuls.data.pojo.vo.AppInfoShowVO;
import io.nuls.data.pojo.vo.TablespaceInfoListVO;
import io.nuls.data.service.AppInfoService;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.AppInfoAPI;
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
 * 【应用】控制器
 *
 * @author Long
 * @date 2020/03/16
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/appInfo")
public class AppInfoController extends AbstractController implements AppInfoAPI {

    @Autowired
    private AppInfoService appInfoService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppInfoShowVO> save(@Valid @RequestBody AppInfoAddDTO appInfoAddDTO) throws Exception {
        AppInfoPO appInfo = appInfoService.save(appInfoAddDTO);
        return ResponseEntity.created(new URI(WebConst.API_PATH + "/appInfo/" + appInfo.getId()))
            .body(AppInfoMapper.INSTANCE.toShowVO(appInfo));
    }

    @Override
    @PutMapping
    public ResponseEntity<AppInfoShowVO> update(@Valid @RequestBody AppInfoUpdateDTO appInfoUpdateDTO) {
        AppInfoPO appInfo = appInfoService.update(appInfoUpdateDTO);
        return ResponseEntity.ok(AppInfoMapper.INSTANCE.toShowVO(appInfo));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<AppInfoListVO>> list(@Valid AppInfoQO appInfoQO) {
        PageVO<AppInfoListVO> page = appInfoService.list(appInfoQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/options")
    public ResponseEntity<List<OptionVO<Integer, String>>> findOptions(OptionQO<Integer, String> qo) {
        List<OptionVO<Integer, String>> options = appInfoService.findOptions(qo);
        return ResponseEntity.ok(options);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<AppInfoShowVO> show(@PathVariable Integer id) {
        AppInfoShowVO appInfoShowVO = appInfoService.show(id);
        return ResponseEntity.ok(appInfoShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        int count = appInfoService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody Integer[] id) {
        if(ArrayUtils.isEmpty(id)){
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = appInfoService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping(value = "/{id}/tablespaceInfo")
    public ResponseEntity<List<TablespaceInfoListVO>> fetchTablespaceInfoList(@PathVariable Integer id) {
        AppInfoPO appInfo = appInfoService.getAppInfo(id, true, true);
        List<TablespaceInfoPO> list = appInfo.getTablespaceInfoPOList();
        return ResponseEntity.ok(TablespaceInfoMapper.INSTANCE.toListVOList(list));
    }

    @Override
    @PostMapping(value = "/{id}/tablespaceInfo")
    public ResponseEntity<Integer> addTablespaceInfo(@PathVariable Integer id,
                        @RequestBody Integer[] tablespaceId) {
        int count = appInfoService.addTablespaceInfo(id, tablespaceId);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping(value = "/{id}/tablespaceInfo")
    public ResponseEntity<Integer> removeTablespaceInfo(@PathVariable Integer id,
                        @RequestBody Integer[] tablespaceId) {
        int count = appInfoService.removeTablespaceInfo(id, tablespaceId);
        return ResponseEntity.ok(count);
    }

}


