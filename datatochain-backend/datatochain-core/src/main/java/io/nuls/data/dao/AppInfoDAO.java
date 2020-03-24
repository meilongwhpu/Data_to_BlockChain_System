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

    List<OptionVO<Integer, String>> findOptions(OptionQO<Integer, String> qo);

    int getCountByTablespaceInfo(Integer tablespaceId);

    int addTablespaceInfo(@Param("appId")Integer appId,@Param("tablespaceId")Integer tablespaceId);

    int removeTablespaceInfo(@Param("appId")Integer appId,@Param("tablespaceId")Integer[] tablespaceId);

    int removeAllTablespaceInfo(Integer appId);


}



