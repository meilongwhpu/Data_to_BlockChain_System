package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.nuls.common.validator.Const;
import io.nuls.data.constant.AllowNull;
import io.nuls.data.constant.FieldType;
import io.nuls.data.constant.IsKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static io.nuls.data.pojo.example.TablefieldInfoExample.*;

/**
 * 修改【表字段】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "修改【表字段】的参数")
public class TablefieldInfoUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID,example = E_ID,required = true)
    @NotNull
    private Integer id;

    @ApiModelProperty(notes = N_TABLE_ID,example = E_TABLE_ID,required = true)
    @NotNull
    private Integer tableId;

    @ApiModelProperty(notes = N_FIELD_NAME,example = E_FIELD_NAME)
    @Length(max = 50)
    private String fieldName;

    @ApiModelProperty(notes = N_FIELD_DESC,example = E_FIELD_DESC)
    @Length(max = 50)
    private String fieldDesc;

    @ApiModelProperty(notes = N_FIELD_TYPE,example = E_FIELD_TYPE,required = true, allowableValues = FieldType.VALUES_STR)
    @NotNull
    @Const(constClass = FieldType.class)
    private Integer fieldType;

    @ApiModelProperty(notes = N_FIELD_LENGTH,example = E_FIELD_LENGTH,required = true)
    @NotNull
    private Integer fieldLength;

    @ApiModelProperty(notes = N_FIELD_DECIMAL_LENGTH,example = E_FIELD_DECIMAL_LENGTH)
    private Integer fieldDecimalLength;

    @ApiModelProperty(notes = N_FIELD_IS_KEY,example = E_FIELD_IS_KEY, allowableValues = IsKey.VALUES_STR)
    @Const(constClass = IsKey.class)
    private Integer fieldIsKey;

    @ApiModelProperty(notes = N_ALLOW_NULL,example = E_ALLOW_NULL,required = true, allowableValues = AllowNull.VALUES_STR)
    @NotNull
    @Const(constClass = AllowNull.class)
    private Integer allowNull;

    @ApiModelProperty(notes = N_FIELD_DEFAULT_VALUE,example = E_FIELD_DEFAULT_VALUE)
    @Length(max = 50)
    private String fieldDefaultValue;

    @ApiModelProperty(notes = N_CREATOR_ID,example = E_CREATOR_ID)
    @Length(max = 50)
    private String creatorId;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return this.tableId;
    }

    public void setTableId(Integer tableId) {
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

    public Integer getFieldLength() {
        return this.fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }

    public Integer getFieldDecimalLength() {
        return this.fieldDecimalLength;
    }

    public void setFieldDecimalLength(Integer fieldDecimalLength) {
        this.fieldDecimalLength = fieldDecimalLength;
    }

    public Integer getFieldIsKey() {
        return this.fieldIsKey;
    }

    public void setFieldIsKey(Integer fieldIsKey) {
        this.fieldIsKey = fieldIsKey;
    }

    public Integer getAllowNull() {
        return this.allowNull;
    }

    public void setAllowNull(Integer allowNull) {
        this.allowNull = allowNull;
    }

    public String getFieldDefaultValue() {
        return this.fieldDefaultValue;
    }

    public void setFieldDefaultValue(String fieldDefaultValue) {
        this.fieldDefaultValue = fieldDefaultValue;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }


}

