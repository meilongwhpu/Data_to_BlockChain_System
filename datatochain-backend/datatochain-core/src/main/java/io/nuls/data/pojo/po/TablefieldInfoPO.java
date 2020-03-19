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
    private String id;

    /**
     * 表结构ID
     */
    private String tableId;

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
    private String fieldLength;

    /**
     * 是否允许为空
     * @see io.nuls.data.constant.AllowNull
     */
    private Integer allowNull;

    /**
     * 创建者ID
     */
    private String creatorId;

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


}

