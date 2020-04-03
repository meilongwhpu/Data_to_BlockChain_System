
package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import static io.nuls.data.pojo.example.Data2ChainExample.*;

/**
 * 验证数字签名【应用】的参数
 *
 * @author Edward
 */
@ApiModel(description = "验证数字签名【应用】")
public class AppVeifyDigestDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_APP_KEY_DESC, example = E_APP_KEY, required = true)
    @NotNull
    private String appKey;

    @ApiModelProperty(notes = N_BODY_DESC, example = E_BODY, required = true)
    @NotNull
    private String body;

    @ApiModelProperty(notes = N_DIGEST_DESC, example = N_DIGEST, required = true)
    @NotNull
    private String digest;

}


