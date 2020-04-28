package io.nuls.data.dao;

import io.nuls.common.dao.DAO;
import io.nuls.data.pojo.po.TableColumnsPo;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.qo.TableDataQO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 【应用】数据库操作
 *
 * @author Long
 * @date 2020/03/16
 */
@Repository
@Mapper
public interface TableDataDAO  extends DAO {


    public List<Map<String,Object>> queryTableDataByCondition(TableDataQO tableDataQO);

    public int insertData(@Param("tableName") String tableName,@Param("tableDataMap") Map<String, Object> tableDataMap);

    public int deleteData(TableDataQO tableDataQO);

}



