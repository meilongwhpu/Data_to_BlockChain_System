package io.nuls.data.dao;

import io.nuls.common.dao.DAO;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【表空间】数据库操作
 *
 * @author Long
 * @date 2020/03/16
 */
@Repository
@Mapper
public interface TablespaceInfoDAO extends DAO<TablespaceInfoPO> {

    List<OptionVO<String, String>> findOptions(OptionQO<String, String> qo);

    List<TablespaceInfoPO> findByAppInfo(String appId);


}



