package io.nuls.data.pojo.qo;

import io.nuls.common.pojo.qo.PageQO;

import java.util.List;

/**
 * 查询【表空间】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
public class TableDataQO extends PageQO {

    private List<String> columnsArray;

    private String tableName;

    public List<String> getColumnsArray() {
        return columnsArray;
    }

    public void setColumnsArray(List<String> columnsArray) {
        this.columnsArray = columnsArray;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

