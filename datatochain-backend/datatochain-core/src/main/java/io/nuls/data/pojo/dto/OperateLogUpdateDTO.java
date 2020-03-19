package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 50)
    private String id;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


}

