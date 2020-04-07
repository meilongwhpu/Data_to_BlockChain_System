package io.nuls.data.service;

import io.nuls.common.util.StringUtils;
import io.nuls.data.constant.FieldType;
import io.nuls.data.constant.MySqlTypeConstant;
import io.nuls.data.dao.*;
import io.nuls.data.pojo.dto.TableDataAddDTO;
import io.nuls.data.pojo.dto.TablespaceInfoAddDTO;
import io.nuls.data.pojo.po.*;
import io.nuls.data.pojo.qo.TableDataQO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【创建表结构服务
 *
 * @author Long
 * @date 2020/03/23
 */
@Service
public class TableDataManagerService {

    private static final Logger log = LoggerFactory.getLogger(TableDataManagerService.class);

    @Autowired
    private TableDataDAO tableDataDAO;

    @Autowired
    private TablefieldInfoDAO tablefieldInfoDAO;

    @Autowired
    private TablestructureInfoDAO tablestructureInfoDAO;

    @Autowired
    private TablespaceInfoDAO tablespaceInfoDAO;

    @Autowired
    private SqlSession sqlSession;

    public List<Map<String, String>>  queryTableHeader(int tableId) {
        // 用于存表头信息
        List<Map<String, String>> tableHeaderList=new ArrayList<Map<String, String>>();

        TablestructureInfoPO tablestructureInfoPO=tablestructureInfoDAO.findById(tableId);
        TablespaceInfoPO tablespaceInfoPO=tablespaceInfoDAO.findById(tablestructureInfoPO.getTablespaceId());
        //组装表名：表空间_表名称
        String completeTableName= tablespaceInfoPO.getTablespaceName()+"_"+tablestructureInfoPO.getTableName();
        tableHeaderList=getTableInfo(completeTableName);

/*
        List<TablefieldInfoPO> tablefieldInfoPOList=tablefieldInfoDAO.findByTableId(tableId);
        for(TablefieldInfoPO tablefieldInfoPO :tablefieldInfoPOList){
            Map<String, String> tableHeaderMap = new HashMap<String, String>();
            tableHeaderMap.put("label",tablefieldInfoPO.getFieldDesc());
            tableHeaderMap.put("prop",tablefieldInfoPO.getFieldName());
            tableHeaderList.add(tableHeaderMap);
        }
*/

        return tableHeaderList;
    }

    public List<Map<String,Object>>  queryTableData(int tableId) {
        TablestructureInfoPO tablestructureInfoPO=tablestructureInfoDAO.findById(tableId);
        TablespaceInfoPO tablespaceInfoPO=tablespaceInfoDAO.findById(tablestructureInfoPO.getTablespaceId());
        //组装表名：表空间_表名称
        String completeTableName= tablespaceInfoPO.getTablespaceName()+"_"+tablestructureInfoPO.getTableName();
        // 获取表字段名
        List<String> columnsList=getTableColumns(completeTableName);

        List<Map<String,Object>> dataList =new ArrayList<Map<String,Object>>();
        TableDataQO tableDataQO=new TableDataQO();
        tableDataQO.setColumnsArray(columnsList);
        tableDataQO.setTableName(completeTableName);
        dataList= tableDataDAO.findListByQuery(tableDataQO);

        return dataList;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void addTableValue(TableDataAddDTO tableDataAddDTO) {
        TablestructureInfoPO tablestructureInfoPO=tablestructureInfoDAO.findById(tableDataAddDTO.getTableId());
        TablespaceInfoPO tablespaceInfoPO=tablespaceInfoDAO.findById(tablestructureInfoPO.getTablespaceId());
        //组装表名：表空间_表名称
        String completeTableName= tablespaceInfoPO.getTablespaceName()+"_"+tablestructureInfoPO.getTableName();
        List<TablefieldInfoPO> tablefieldInfoPOList=tablefieldInfoDAO.findByTableId(tableDataAddDTO.getTableId());
        Map<String, String> colums=new HashMap<String, String>();
        for(TablefieldInfoPO tablefieldInfoPO:tablefieldInfoPOList){
            colums.put(tablefieldInfoPO.getFieldName(),FieldType.valueToDesc(tablefieldInfoPO.getFieldType()));
        }

        Map<String, Object> tableDataMap =new HashMap<String, Object>();
        for (Map.Entry<String,String>  entry : tableDataAddDTO.getDataValues().entrySet()) {
            TableDataPO tableDataPO = new TableDataPO();
            tableDataPO.setValues(entry.getValue());
            tableDataPO.setType(colums.get(entry.getKey()));
            tableDataMap.put(entry.getKey(),tableDataPO);
        }
        tableDataDAO.insertData(tableDataMap);
    }

    private List<String> getTableColumns(String tableName){
        List<String> columnsList=new ArrayList<String>();
        Connection connection = null;
        try {
            connection = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet result=databaseMetaData.getColumns(null,null,tableName,null);
            while (result.next()){
                String columnName = result.getString(4); // COLUMN_NAME
                columnsList.add(columnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnsList;
    }

    private List<Map<String, String>> getTableInfo(String tableName){
        // 用于存表头信息
        List<Map<String, String>> tableHeaderList=new ArrayList<Map<String, String>>();
        Connection connection = null;
        try {
            connection = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet result=databaseMetaData.getColumns(null,null,tableName,null);
            while (result.next()){
                //TableHeaderListPO tableHeaderListPO=new TableHeaderListPO();
                String columnName = result.getString(4); // COLUMN_NAME
                String columnComment=result.getString(12);
                Map<String, String> tableHeaderMap = new HashMap<String, String>();
                if(StringUtils.isBlank(columnComment)){
                    columnComment=columnName;
                }
                tableHeaderMap.put("label",columnComment);
                tableHeaderMap.put("prop",columnName);
                tableHeaderList.add(tableHeaderMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableHeaderList;
    }

}
