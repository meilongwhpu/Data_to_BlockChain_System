package io.nuls.data.dao;

import io.nuls.common.dao.DAO;
import io.nuls.data.pojo.po.MetaDataPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MetaDataDAO  extends DAO<MetaDataPO> {

    List<MetaDataPO> findByTableName(@Param("tableName") String tableName);
}
