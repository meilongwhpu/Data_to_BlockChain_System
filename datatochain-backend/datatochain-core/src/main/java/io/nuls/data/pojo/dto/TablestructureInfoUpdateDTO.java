package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static io.nuls.data.pojo.example.TablestructureInfoExample.*;

/**
 * 修改【表结构】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "修改【表结构】的参数")
public class TablestructureInfoUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID,example = E_ID,required = true)
    @NotNull
    private Integer id;

    @ApiModelProperty(notes = N_TABLE_NAME,example = E_TABLE_NAME,required = true)
    @NotNull
    @Length(max = 50)
    private String tableName;

    @ApiModelProperty(notes = N_TABLE_DESC,example = E_TABLE_DESC)
    @Length(max = 50)
    private String tableDesc;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return this.tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }


}

