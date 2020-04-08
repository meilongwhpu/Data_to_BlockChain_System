
package io.nuls.data.web.rest;

import io.nuls.data.pojo.dto.AppVeifyDigestDTO;
import io.nuls.data.pojo.vo.AppInfoShowVO;
import io.nuls.data.web.AbstractController;
import io.nuls.data.web.api.Data2ChainAPI;
import io.nuls.data.web.constant.WebConst;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 【应用】控制器
 *
 * @author dward
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/data2Chain")
public class Data2ChainController extends AbstractController implements Data2ChainAPI {

    @Override
    @PostMapping
    public ResponseEntity<AppInfoShowVO> verifyDigest(@Valid @RequestBody AppVeifyDigestDTO appVeifyDigestDTO) throws Exception {
        //TODO
        return null;
    }

}


