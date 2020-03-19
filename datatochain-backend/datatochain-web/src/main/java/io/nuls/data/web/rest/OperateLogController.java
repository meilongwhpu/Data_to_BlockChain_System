
package io.nuls.data.web.rest;

import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.pojo.qo.OperateLogQO;
import io.nuls.data.pojo.vo.OperateLogListVO;
import io.nuls.data.service.OperateLogService;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.OperateLogAPI;
import io.nuls.data.web.constant.WebConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * 【操作日志】控制器
 *
 * @author Long
 * @date 2020/03/16
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/operateLog")
public class OperateLogController extends AbstractController implements OperateLogAPI {

    @Autowired
    private OperateLogService operateLogService;

    @Override
    @GetMapping
    public ResponseEntity<PageVO<OperateLogListVO>> list(@Valid OperateLogQO operateLogQO) {
        PageVO<OperateLogListVO> page = operateLogService.list(operateLogQO);
        return ResponseEntity.ok(page);
    }

}


