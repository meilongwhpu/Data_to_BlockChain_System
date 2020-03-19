package io.nuls.data.dao;

import io.nuls.common.dao.DAO;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.data.pojo.po.TablestructureInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【表结构】数据库操作
 *
 * @author Long
 * @date 2020/03/16
 */
@Repository
@Mapper
public interface TablestructureInfoDAO extends DAO<TablestructureInfoPO> {

    List<OptionVO<String, String>> findOptions(OptionQO<String, String> qo);

    int getCountByTablespaceId(String tablespaceId);


}



