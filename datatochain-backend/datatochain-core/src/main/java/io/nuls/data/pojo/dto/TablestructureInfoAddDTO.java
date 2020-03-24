
package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.nuls.common.validator.Const;
import io.nuls.data.constant.IsCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static io.nuls.data.pojo.example.TablestructureInfoExample.*;

/**
 * 新增【表结构】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "新增【表结构】的参数")
public class TablestructureInfoAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_TABLESPACE_ID, example = E_TABLESPACE_ID, required = true)
    @NotNull
    private Integer tablespaceId;

    @ApiModelProperty(notes = N_TABLE_NAME, example = E_TABLE_NAME, required = true)
    @NotNull
    @Length(max = 50)
    private String tableName;

    @ApiModelProperty(notes = N_TABLE_DESC, example = E_TABLE_DESC)
    @Length(max = 50)
    private String tableDesc;

    @ApiModelProperty(notes = N_CREATOR_ID, example = E_CREATOR_ID)
    @Length(max = 50)
    private String creatorId;

    @ApiModelProperty(notes = N_IS_CREATE, example = E_IS_CREATE, allowableValues = IsCreate.VALUES_STR)
    @Const(constClass = IsCreate.class)
    private Boolean isCreate;


    public Integer getTablespaceId() {
        return this.tablespaceId;
    }

    public void setTablespaceId(Integer tablespaceId) {
        this.tablespaceId = tablespaceId;
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

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Boolean getIsCreate() {
        return this.isCreate;
    }

    public void setIsCreate(Boolean isCreate) {
        this.isCreate = isCreate;
    }

}


