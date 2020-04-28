package io.nuls.data.pojo.vo;

import io.nuls.common.pojo.vo.AbstractVO;

public class TableInfoVO extends AbstractVO {

    private Integer tableId;

    private String tableSpaceName;

    private String tableName;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableSpaceName() {
        return tableSpaceName;
    }

    public void setTableSpaceName(String tableSpaceName) {
        this.tableSpaceName = tableSpaceName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
