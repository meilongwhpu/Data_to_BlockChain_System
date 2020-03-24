package io.nuls.data.pojo.vo;

import io.nuls.common.pojo.vo.AbstractVO;
import io.nuls.data.constant.AllowNull;
import io.nuls.data.constant.FieldType;
import io.nuls.data.constant.IsKey;
import io.nuls.data.pojo.example.TablestructureInfoExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static io.nuls.data.pojo.example.TablefieldInfoExample.*;

/**
 * 【表字段】详情展示对象
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "【表字段】详情展示对象")
public class TablefieldInfoShowVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID,example = E_ID)
    private Integer id;

    @ApiModelProperty(notes = N_TABLE_ID,example = E_TABLE_ID)
    private Integer tableId;

    @ApiModelProperty(notes = N_FIELD_NAME,example = E_FIELD_NAME)
    private String fieldName;

    @ApiModelProperty(notes = N_FIELD_DESC,example = E_FIELD_DESC)
    private String fieldDesc;

    @ApiModelProperty(notes = N_FIELD_TYPE,example = E_FIELD_TYPE, allowableValues = FieldType.VALUES_STR)
    private Integer fieldType;

    @ApiModelProperty(notes = N_FIELD_LENGTH,example = E_FIELD_LENGTH)
    private Integer fieldLength;

    @ApiModelProperty(notes = N_FIELD_DECIMAL_LENGTH,example = E_FIELD_DECIMAL_LENGTH)
    private Integer fieldDecimalLength;

    @ApiModelProperty(notes = N_FIELD_IS_KEY,example = E_FIELD_IS_KEY, allowableValues = IsKey.VALUES_STR)
    private Boolean fieldIsKey;

    @ApiModelProperty(notes = N_ALLOW_NULL,example = E_ALLOW_NULL, allowableValues = AllowNull.VALUES_STR)
    private Integer allowNull;

    @ApiModelProperty(notes = N_FIELD_DEFAULT_VALUE,example = E_FIELD_DEFAULT_VALUE)
    private String fieldDefaultValue;

    @ApiModelProperty(notes = N_CREATOR_ID,example = E_CREATOR_ID)
    private String creatorId;

    @ApiModelProperty(notes = TablestructureInfoExample.N_TABLE_NAME,example = TablestructureInfoExample.E_TABLE_NAME)
    private String tableName;


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

    public Boolean getFieldIsKey() {
        return this.fieldIsKey;
    }

    public void setFieldIsKey(Boolean fieldIsKey) {
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

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }



}

