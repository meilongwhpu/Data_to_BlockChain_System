package io.nuls.data.service;

import io.nuls.data.constant.FieldType;
import io.nuls.data.constant.MySqlTypeConstant;
import io.nuls.data.dao.*;
import io.nuls.data.pojo.po.CreateTableParamPO;
import io.nuls.data.pojo.po.TablefieldInfoPO;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.po.TablestructureInfoPO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
public class CreateTableManagerService {

    private static final Logger log = LoggerFactory.getLogger(CreateTableManagerService.class);

    @Autowired
    private OperateTableDAO operateTableDAO;

    @Autowired
    private TablefieldInfoDAO tablefieldInfoDAO;

    @Autowired
    private TablestructureInfoDAO tablestructureInfoDAO;

    @Autowired
    private TablespaceInfoDAO tablespaceInfoDAO;

    public void createMysqlTable(int tableId) {
        // 用于存需要创建的表名+结构
        Map<String, List<Object>> newTableMap = new HashMap<String, List<Object>>();
        // 用于存新增表的字段
        List<Object> newFieldList = new ArrayList<Object>();

        TablestructureInfoPO tablestructureInfoPO=tablestructureInfoDAO.findById(tableId);
        TablespaceInfoPO tablespaceInfoPO=tablespaceInfoDAO.findById(tablestructureInfoPO.getTablespaceId());
/*        int count=operateTableSpaceDAO.findTableSpaceNameByName(tablespaceInfoPO.getTablespaceName());
        if(count==0){
            operateTableSpaceDAO.createTableSpace(tablespaceInfoPO.getTablespaceName());
        }*/

        this.tableFieldsConstruct(tableId,newFieldList);
        //组装表名：表空间_表名称
        String completeTableName= tablespaceInfoPO.getTablespaceName()+"_"+tablestructureInfoPO.getTableName();
        newTableMap.put(completeTableName,newFieldList);
        createTableByMap(newTableMap);
        tablestructureInfoDAO.updateIsCreate(1,tableId);
    }

    private void tableFieldsConstruct(int tableId,List<Object> newFieldList){
        List<TablefieldInfoPO> tablefieldInfoPOList=tablefieldInfoDAO.findByTableId(tableId);
        for(TablefieldInfoPO tablefieldInfoPO :tablefieldInfoPOList){
            CreateTableParamPO paramPO= new CreateTableParamPO();
            paramPO.setFieldName(tablefieldInfoPO.getFieldName());
            paramPO.setFieldType(FieldType.valueToDesc(tablefieldInfoPO.getFieldType()));
            paramPO.setFieldLength(tablefieldInfoPO.getFieldLength());
            paramPO.setFieldDecimalLength(tablefieldInfoPO.getFieldDecimalLength());
            // 主键或唯一键时设置必须不为null
            if (tablefieldInfoPO.getFieldIsKey()==1) {
                paramPO.setFieldIsNull(false);
            } else {
                paramPO.setFieldIsNull(tablefieldInfoPO.getAllowNull()==0?true:false);
            }
            paramPO.setFieldIsKey(tablefieldInfoPO.getFieldIsKey()==1?true:false);
            paramPO.setFieldDefaultValue(tablefieldInfoPO.getFieldDefaultValue());

            paramPO.setFieldIsAutoIncrement(false);
            paramPO.setFieldIsUnique(false);
            paramPO.setFieldDesc(tablefieldInfoPO.getFieldDesc());
            int length =  MySqlTypeConstant.typeDescToLength(FieldType.valueToDesc(tablefieldInfoPO.getFieldType()).toLowerCase());
            paramPO.setFileTypeLength(length);
            newFieldList.add(paramPO);
        }
    }

    /**
     * 根据map结构创建表
     *
     * @param newTableMap
     *            用于存需要创建的表名+结构
     */
    private void createTableByMap(Map<String, List<Object>> newTableMap) {
        // 做创建表操作
        if (newTableMap.size() > 0) {
            for (Map.Entry<String, List<Object>> entry : newTableMap.entrySet()) {
                Map<String, List<Object>> map = new HashMap<String, List<Object>>();
                map.put(entry.getKey(), entry.getValue());
                log.info("开始创建表：" + entry.getKey());
                operateTableDAO.createTable(map);
                log.info("完成创建表：" + entry.getKey());
            }
        }
    }


}
