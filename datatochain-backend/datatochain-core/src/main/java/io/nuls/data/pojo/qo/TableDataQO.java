package io.nuls.data.pojo.qo;

import io.nuls.common.pojo.qo.PageQO;

import java.util.List;
import java.util.Map;

/**
 * 查询【表空间】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
public class TableDataQO extends PageQO {

    /**
     * 主键ID
     */
    private Integer tableId;

    private List<String> columnsArray;

    private String tableName;

    private Map<String,Object> conditionMap;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

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

    public Map<String, Object> getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Map<String, Object> conditionMap) {
        this.conditionMap = conditionMap;
    }
}

