
package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.nuls.common.validator.Const;
import io.nuls.data.constant.AllowNull;
import io.nuls.data.constant.FieldType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static io.nuls.data.pojo.example.TablefieldInfoExample.*;

/**
 * 新增【表字段】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "新增【表字段】的参数")
public class TablefieldInfoAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_TABLE_ID, example = E_TABLE_ID, required = true)
    @NotNull
    @Length(max = 50)
    private String tableId;

    @ApiModelProperty(notes = N_FIELD_NAME, example = E_FIELD_NAME)
    @Length(max = 50)
    private String fieldName;

    @ApiModelProperty(notes = N_FIELD_DESC, example = E_FIELD_DESC)
    @Length(max = 50)
    private String fieldDesc;

    @ApiModelProperty(notes = N_FIELD_TYPE, example = E_FIELD_TYPE, required = true, allowableValues = FieldType.VALUES_STR)
    @NotNull
    @Const(constClass = FieldType.class)
    private Integer fieldType;

    @ApiModelProperty(notes = N_FIELD_LENGTH, example = E_FIELD_LENGTH, required = true)
    @NotNull
    @Length(max = 10)
    private String fieldLength;

    @ApiModelProperty(notes = N_ALLOW_NULL, example = E_ALLOW_NULL, required = true, allowableValues = AllowNull.VALUES_STR)
    @NotNull
    @Const(constClass = AllowNull.class)
    private Integer allowNull;

    @ApiModelProperty(notes = N_CREATOR_ID, example = E_CREATOR_ID)
    @Length(max = 50)
    private String creatorId;


    public String getTableId() {
        return this.tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDesc() {
        return this.fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public Integer getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldLength() {
        return this.fieldLength;
    }

    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }

    public Integer getAllowNull() {
        return this.allowNull;
    }

    public void setAllowNull(Integer allowNull) {
        this.allowNull = allowNull;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

}


