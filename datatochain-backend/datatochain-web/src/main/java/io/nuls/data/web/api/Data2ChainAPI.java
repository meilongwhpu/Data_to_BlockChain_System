package io.nuls.data.web.api;

import io.nuls.data.pojo.dto.AppVeifyDigestDTO;
import io.nuls.data.pojo.vo.AppInfoShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * 【数据上链】API
 * <p>swagger接口文档
 *
 * @author Edward
 */
@Api(tags = "【数据上链】数据上链接口")
public interface Data2ChainAPI {

    /**
     * 验证数字签名
     */
    @ApiOperation(value = "验证数字签名【数据上链】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appVeifyDigestDTO", dataType = "AppVeifyDigestDTO", value = "数字签名验证参数", paramType = "body"),
    })
    ResponseEntity<AppInfoShowVO> verifyDigest(AppVeifyDigestDTO appVeifyDigestDTO) throws Exception;


}

