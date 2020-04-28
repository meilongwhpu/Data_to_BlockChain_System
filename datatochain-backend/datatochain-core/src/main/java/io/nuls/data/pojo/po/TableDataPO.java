package io.nuls.data.pojo.po;

import io.nuls.common.pojo.po.AbstractPO;

import java.util.Date;

/**
 * 表空间
 * <p>表空间信息表
 *
 * @author Long
 * @date 2020/03/16
 */
public class TableDataPO extends AbstractPO {

    /**
     * 主键ID
     */
    private Integer tableId;

    private Object values;
    private String type;

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
}

