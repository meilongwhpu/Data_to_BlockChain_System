package io.nuls.data.pojo.vo;

import io.nuls.common.pojo.vo.AbstractVO;
import io.nuls.data.constant.AllowNull;
import io.nuls.data.constant.FieldType;
import io.nuls.data.pojo.example.TablestructureInfoExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static io.nuls.data.pojo.example.TablefieldInfoExample.*;

/**
 * 【表字段】列表展示对象
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "【表字段】列表展示对象")
public class TablefieldInfoListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_TABLE_ID, example = E_TABLE_ID)
    private String tableId;

    @ApiModelProperty(notes = N_FIELD_NAME, example = E_FIELD_NAME)
    private String fieldName;

    @ApiModelProperty(notes = N_FIELD_DESC, example = E_FIELD_DESC)
    private String fieldDesc;

    @ApiModelProperty(notes = N_FIELD_TYPE, example = E_FIELD_TYPE, allowableValues = FieldType.VALUES_STR)
    private Integer fieldType;

    @ApiModelProperty(notes = N_FIELD_LENGTH, example = E_FIELD_LENGTH)
    private String fieldLength;

    @ApiModelProperty(notes = N_ALLOW_NULL, example = E_ALLOW_NULL, allowableValues = AllowNull.VALUES_STR)
    private Integer allowNull;

    @ApiModelProperty(notes = N_CREATOR_ID, example = E_CREATOR_ID)
    private String creatorId;

    @ApiModelProperty(notes = TablestructureInfoExample.N_TABLE_NAME, example = TablestructureInfoExample.E_TABLE_NAME)
    private String tableName;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }



}

