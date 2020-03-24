package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import static io.nuls.data.pojo.example.OperateLogExample.*;

/**
 * 修改【操作日志】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "修改【操作日志】的参数")
public class OperateLogUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID,example = E_ID,required = true)
    @NotNull
    private Integer id;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}

