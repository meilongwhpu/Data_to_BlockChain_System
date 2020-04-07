package io.nuls.data.pojo.vo;

import io.nuls.data.pojo.po.TableHeaderListPO;

import java.util.List;
import java.util.Map;

/**
 * 登录用户信息
 *
 * @author Long
 * @date 2020/03/16
 */
public class TableDataListVO {

    private List<Map<String, String>> tableHeader;
    private List<Map<String,Object>> tableData;

    public List<Map<String, String>> getTableHeader() {
        return tableHeader;
    }

    public void setTableHeader(List<Map<String, String>> tableHeader) {
        this.tableHeader = tableHeader;
    }

    public List<Map<String, Object>> getTableData() {
        return tableData;
    }

    public void setTableData(List<Map<String, Object>> tableData) {
        this.tableData = tableData;
    }
}

