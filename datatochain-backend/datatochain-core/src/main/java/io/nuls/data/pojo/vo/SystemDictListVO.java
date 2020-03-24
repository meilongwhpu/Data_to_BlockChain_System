package io.nuls.data.pojo.vo;

import io.nuls.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static io.nuls.data.pojo.example.SystemDictExample.*;

/**
 * 【系统参数】列表展示对象
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "【系统参数】列表展示对象")
public class SystemDictListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private Integer id;

    @ApiModelProperty(notes = N_DICT_KEY, example = E_DICT_KEY)
    private String dictKey;

    @ApiModelProperty(notes = N_DICT_VALUE, example = E_DICT_VALUE)
    private String dictValue;

    @ApiModelProperty(notes = N_DICT_DESC, example = E_DICT_DESC)
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

