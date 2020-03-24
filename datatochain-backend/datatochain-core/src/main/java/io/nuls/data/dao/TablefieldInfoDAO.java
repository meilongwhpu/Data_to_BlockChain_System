package io.nuls.data.dao;

import io.nuls.common.dao.DAO;
import io.nuls.data.pojo.po.TablefieldInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 【表字段】数据库操作
 *
 * @author Long
 * @date 2020/03/16
 */
@Repository
@Mapper
public interface TablefieldInfoDAO extends DAO<TablefieldInfoPO> {

    int getCountByTableId(Integer tableId);


}



