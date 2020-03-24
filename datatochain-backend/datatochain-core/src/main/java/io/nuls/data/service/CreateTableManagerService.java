package io.nuls.data.service;

import io.nuls.data.dao.OperateTableDAO;
import io.nuls.data.dao.TablefieldInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【创建表结构服务
 *
 * @author Long
 * @date 2020/03/23
 */
public class CreateTableManagerService {


    @Autowired
    private OperateTableDAO operateTableDAO;

    @Autowired
    private TablefieldInfoDAO tablefieldInfoDAO;


    public void createMysqlTable() {
        // 用于存需要创建的表名+结构
        Map<String, List<Object>> newTableMap = new HashMap<String, List<Object>>();

    }

}
