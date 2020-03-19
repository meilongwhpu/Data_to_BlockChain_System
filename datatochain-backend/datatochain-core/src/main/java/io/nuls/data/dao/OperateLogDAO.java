package io.nuls.data.dao;

import io.nuls.common.dao.DAO;
import io.nuls.data.pojo.po.OperateLogPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 【操作日志】数据库操作
 *
 * @author Long
 * @date 2020/03/16
 */
@Repository
@Mapper
public interface OperateLogDAO extends DAO<OperateLogPO> {


}



