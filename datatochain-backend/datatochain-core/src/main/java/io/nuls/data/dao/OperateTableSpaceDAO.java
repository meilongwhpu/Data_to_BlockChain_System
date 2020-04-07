package io.nuls.data.dao;

import io.nuls.data.pojo.po.TableColumnsPo;
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
public interface OperateTableSpaceDAO {

    /**
     * 创建表空间
     * @param tableSpaceName 表空间名称
     */
    public void createTableSpace(@Param("tableSpaceName") String tableSpaceName);

    /**
     * 根据表空间名称查询是否已经存在，存在返回1，不存在返回0
     * @param tableSpaceName 表空间名称
     * @return 存在返回1，不存在返回0
     */
    public int findTableSpaceNameByName(@Param("tableSpaceName") String tableSpaceName);

    /**
     * 根据表空间名称删除库
     * @param tableSpaceName  表空间名称
     */
    public void dropTableSpaceByName(@Param("tableSpaceName") String tableSpaceName);

}



