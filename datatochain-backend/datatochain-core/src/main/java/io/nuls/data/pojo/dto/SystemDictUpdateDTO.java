package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static io.nuls.data.pojo.example.SystemDictExample.*;

/**
 * 修改【系统参数】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "修改【系统参数】的参数")
public class SystemDictUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID,example = E_ID,required = true)
    @NotNull
    private Integer id;

    @ApiModelProperty(notes = N_DICT_KEY,example = E_DICT_KEY,required = true)
    @NotNull
    @Length(max = 100)
    private String dictKey;

    @ApiModelProperty(notes = N_DICT_VALUE,example = E_DICT_VALUE,required = true)
    @NotNull
    @Length(max = 100)
    private String dictValue;

    @ApiModelProperty(notes = N_DICT_DESC,example = E_DICT_DESC)
    @Length(max = 50)
    private String dictDesc;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictKey() {
        return this.dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return this.dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictDesc() {
        return this.dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }


}

