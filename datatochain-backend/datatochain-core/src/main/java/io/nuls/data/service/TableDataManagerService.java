package io.nuls.data.service;

import io.nuls.common.util.UUIDUtil;
import io.nuls.data.constant.ChainType;
import io.nuls.data.constant.FieldType;
import io.nuls.data.constant.SystemDictKey;
import io.nuls.data.dao.*;
import io.nuls.data.pojo.dto.TableDataAddDTO;
import io.nuls.data.pojo.po.*;
import io.nuls.data.pojo.qo.TableDataQO;
import io.nuls.data.pojo.vo.VerifyDataVO;
import io.nuls.data.rpc.impl.ChainBlockForNulsNetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.*;

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
    private MetaDataDAO metaDataDAO;

    @Autowired
    private ChainBlockForNulsNetService chainBlockForNulsNetService;

    public List<Map<String, String>>  queryTableHeader(int tableId) {
        // 用于存表头信息
        List<Map<String, String>> tableHeaderList=new ArrayList<Map<String, String>>();

        TablestructureInfoPO tablestructureInfoPO=tablestructureInfoDAO.findById(tableId);
        TablespaceInfoPO tablespaceInfoPO=tablespaceInfoDAO.findById(tablestructureInfoPO.getTablespaceId());
        //组装表名：表空间_表名称
        String completeTableName= tablespaceInfoPO.getTablespaceName()+"_"+tablestructureInfoPO.getTableName();
        tableHeaderList=getTableInfo(completeTableName);

        //隐藏内部ID字段
        Iterator<Map<String,String>> iterator=tableHeaderList.iterator();
        while (iterator.hasNext()) {
            Map<String,String> map=iterator.next();
            if(map.get("prop").equals(SystemDictKey.INNER_ID_FIELD)){
                iterator.remove();
            }
        }

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

    public VerifyDataVO queryTableDataByInnerid(int tableId, String innerId) {
        List<Map<String,Object>> dataList =new ArrayList<Map<String,Object>>();
        VerifyDataVO verifyDataVO=new VerifyDataVO();
        TablestructureInfoPO tablestructureInfoPO=tablestructureInfoDAO.findById(tableId);
        TablespaceInfoPO tablespaceInfoPO=tablespaceInfoDAO.findById(tablestructureInfoPO.getTablespaceId());
        //组装表名：表空间_表名称
        String completeTableName= tablespaceInfoPO.getTablespaceName()+"_"+tablestructureInfoPO.getTableName();
        // 获取表字段名
        List<String> columnsList=getTableColumns(completeTableName);
        Map<String, Object> conditionMap =new HashMap<String, Object>();
        TableDataPO tableDataPO = new TableDataPO();
        tableDataPO.setValues(innerId);
        tableDataPO.setType(FieldType.VARCHAR.getDesc());
        conditionMap.put(SystemDictKey.INNER_ID_FIELD,tableDataPO);

        TableDataQO tableDataQO=new TableDataQO();
        tableDataQO.setColumnsArray(columnsList);
        tableDataQO.setTableName(completeTableName);
        tableDataQO.setConditionMap(conditionMap);
        dataList =tableDataDAO.queryTableDataByCondition(tableDataQO);
        if(dataList.size()>0){
            Map<String,Object> dataMap=dataList.get(0);
            try {
                VerifyDataPO verifyDataPO= chainBlockForNulsNetService.getDataForChain(dataMap.get(SystemDictKey.INNER_HASH_FIELD).toString());
                if(verifyDataPO!=null){
                    verifyDataVO.setHash(verifyDataPO.getHash());
                    verifyDataVO.setBlockHeight(verifyDataPO.getBlockHeight());
                    verifyDataVO.setRemark(verifyDataPO.getRemark());
                    verifyDataVO.setTime(verifyDataPO.getTime());
                   String isModify="NO";
                    String[] datas= verifyDataPO.getRemark().split(",");
                   for(int i=0;i<datas.length;i++){
                      String[] data= datas[i].split("=");
                       if(!data[1].equals(dataMap.get(data[0]).toString())){
                           isModify="YES";
                           break;
                       }
                   }
                    verifyDataVO.setIsModify(isModify);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return verifyDataVO;
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


        StringBuffer toChainData=new StringBuffer();
        Map<String, Object> tableDataMap =new HashMap<String, Object>();
        for (Map.Entry<String,Object>  entry : tableDataAddDTO.getDataValues().entrySet()) {
            TableDataPO tableDataPO = new TableDataPO();
            tableDataPO.setValues(String.valueOf(entry.getValue()));
            tableDataPO.setType(colums.get(entry.getKey()));
            tableDataMap.put(entry.getKey(),tableDataPO);
            toChainData.append(entry.getKey()+"="+tableDataPO.getValues()+",");
        }


        toChainData.deleteCharAt(toChainData.length()-1);

        //检查是否需要上链
        if(tablespaceInfoPO.getToChain()!= ChainType.NOTOCHAINBLOCK.getValue()){
            try {
                String hash=chainBlockForNulsNetService.saveData("tNULSeBaMvEtDfvZuukDf2mVyfGo3DdiN8KLRG","nuls123456",toChainData.toString());
                log.info("the data for chain :"+toChainData.toString()+" ,hash="+hash);
               //记录上链的交易数据hash值
                TableDataPO tableDataPO = new TableDataPO();
                tableDataPO.setValues(hash);
                tableDataPO.setType(FieldType.VARCHAR.getDesc());
                tableDataMap.put(SystemDictKey.INNER_HASH_FIELD,tableDataPO);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        //生成内部ID
        TableDataPO tableDataPO = new TableDataPO();
        tableDataPO.setValues(UUIDUtil.getUUID16());
        tableDataPO.setType("VARCHAR");
        tableDataMap.put(SystemDictKey.INNER_ID_FIELD,tableDataPO);

        int s=tableDataDAO.insertData(completeTableName,tableDataMap);

    }

    private List<String> getTableColumns(String tableName){
        List<String> columnsList=new ArrayList<String>();
        Connection connection = null;
        try {
            List<MetaDataPO>  metaDataPOS= metaDataDAO.findByTableName(tableName);
            for(MetaDataPO metaDataPO:metaDataPOS){
                columnsList.add(metaDataPO.getColumnName());
            }

/*            connection = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet result=databaseMetaData.getColumns(null,null,tableName,null);
            while (result.next()){
                String columnName = result.getString(4); // COLUMN_NAME
                columnsList.add(columnName);
            }*/
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
            List<MetaDataPO>  metaDataPOS= metaDataDAO.findByTableName(tableName);
            for(MetaDataPO metaDataPO:metaDataPOS){
                Map<String, String> tableHeaderMap = new HashMap<String, String>();
                tableHeaderMap.put("label",metaDataPO.getColumnComment());
                tableHeaderMap.put("prop",metaDataPO.getColumnName());
                tableHeaderList.add(tableHeaderMap);
            }

/*            connection = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
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
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableHeaderList;
    }

    /**
     * 删除【表字段】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteData(Integer tableId,String... innerIds) {
        TablestructureInfoPO tablestructureInfoPO=tablestructureInfoDAO.findById(tableId);
        TablespaceInfoPO tablespaceInfoPO=tablespaceInfoDAO.findById(tablestructureInfoPO.getTablespaceId());
        //组装表名：表空间_表名称
        String completeTableName= tablespaceInfoPO.getTablespaceName()+"_"+tablestructureInfoPO.getTableName();
        TableDataQO tableDataQO=new TableDataQO();
        tableDataQO.setTableName(completeTableName);
        int count = 0;
        for (String innerId : innerIds) {
            Map<String, Object> conditionMap =new HashMap<String, Object>();
            TableDataPO tableDataPO = new TableDataPO();
            tableDataPO.setValues(innerId);
            tableDataPO.setType(FieldType.VARCHAR.getDesc());
            conditionMap.put(SystemDictKey.INNER_ID_FIELD,tableDataPO);
            tableDataQO.setConditionMap(conditionMap);
            count += tableDataDAO.deleteData(tableDataQO);
        }
        return count;
    }

}
