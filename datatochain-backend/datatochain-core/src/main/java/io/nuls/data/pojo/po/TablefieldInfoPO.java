package io.nuls.data.pojo.po;

import io.nuls.common.pojo.po.AbstractPO;

/**
 * 表字段
 * <p>表字段信息
 *
 * @author Long
 * @date 2020/03/16
 */
public class TablefieldInfoPO extends AbstractPO {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 表结构ID
     */
    private Integer tableId;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段描述
     */
    private String fieldDesc;

    /**
     * 字段类型
     * @see io.nuls.data.constant.FieldType
     */
    private Integer fieldType;

    /**
     * 字段长度
     */
    private Integer fieldLength;

    /**
     * 类型小数长度
     */
    private Integer fieldDecimalLength;

    /**
     * 是否为主键
     * @see io.nuls.data.constant.IsKey
     */
    private Boolean fieldIsKey;

    /**
     * 是否允许为空
     * @see io.nuls.data.constant.AllowNull
     */
    private Integer allowNull;

    /**
     * 字段默认值
     */
    private String fieldDefaultValue;

    /**
     * 创建者ID
     */
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


}

