package io.nuls.data.dao;

import io.nuls.common.dao.DAO;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.data.pojo.po.AppInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【应用】数据库操作
 *
 * @author Long
 * @date 2020/03/16
 */
@Repository
@Mapper
public interface AppInfoDAO extends DAO<AppInfoPO> {

    List<OptionVO<String, String>> findOptions(OptionQO<String, String> qo);

    int getCountByTablespaceInfo(String tablespaceId);

    int addTablespaceInfo(@Param("appId")String appId,@Param("tablespaceId")String tablespaceId);

    int removeTablespaceInfo(@Param("appId")String appId,@Param("tablespaceId")String[] tablespaceId);

    int removeAllTablespaceInfo(String appId);


}



