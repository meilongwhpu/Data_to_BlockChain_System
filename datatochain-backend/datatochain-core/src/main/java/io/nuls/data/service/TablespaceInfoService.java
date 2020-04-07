package io.nuls.data.service;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.dao.AppInfoDAO;
import io.nuls.data.dao.TablespaceInfoDAO;
import io.nuls.data.dao.TablestructureInfoDAO;
import io.nuls.data.pojo.dto.TablespaceInfoAddDTO;
import io.nuls.data.pojo.dto.TablespaceInfoUpdateDTO;
import io.nuls.data.pojo.mapper.TablespaceInfoMapper;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.qo.TablespaceInfoQO;
import io.nuls.data.pojo.vo.TablespaceInfoListVO;
import io.nuls.data.pojo.vo.TablespaceInfoShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 【表空间】增删改查服务
 *
 * @author Long
 * @date 2020/03/16
 */
@Service
public class TablespaceInfoService {

    @Autowired
    private AppInfoDAO appInfoDAO;
    @Autowired
    private TablespaceInfoDAO tablespaceInfoDAO;
    @Autowired
    private TablestructureInfoDAO tablestructureInfoDAO;


    /**
     * 新增【表空间】
     *
     * @param tablespaceInfoDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TablespaceInfoPO save(TablespaceInfoAddDTO tablespaceInfoDTO) {
        TablespaceInfoPO tablespaceInfo = TablespaceInfoMapper.INSTANCE.fromAddDTO(tablespaceInfoDTO);
        tablespaceInfo.setUpdateTime(new Date());
        tablespaceInfo.setCreatorId("");
        tablespaceInfoDAO.save(tablespaceInfo);
        return tablespaceInfo;
    }

    /**
     * 修改【表空间】
     *
     * @param tablespaceInfoUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TablespaceInfoPO update(TablespaceInfoUpdateDTO tablespaceInfoUpdateDTO) {
        Integer id = tablespaceInfoUpdateDTO.getId();
        TablespaceInfoPO tablespaceInfo = this.getTablespaceInfo(id, true);
        TablespaceInfoMapper.INSTANCE.setUpdateDTO(tablespaceInfo, tablespaceInfoUpdateDTO);
        tablespaceInfoDAO.update(tablespaceInfo);
        return tablespaceInfo;
    }

    /**
     * 查询分页列表
     *
     * @param tablespaceInfoQO
     * @return
     */
    public PageVO<TablespaceInfoListVO> list(TablespaceInfoQO tablespaceInfoQO) {
        PageVO<TablespaceInfoListVO> page = tablespaceInfoDAO.findByPage(tablespaceInfoQO);
        return page;
    }

    /**
     * 查询【表空间】选项列表
     *
     * @return
     */
    public List<OptionVO<Integer, String>> findOptions(OptionQO<Integer, String> qo) {
        List<OptionVO<Integer, String>> options = tablespaceInfoDAO.findOptions(qo);
        return options;
    }

    /**
     * 根据主键获取【表空间】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public TablespaceInfoPO getTablespaceInfo(Integer id, boolean force){
        TablespaceInfoPO tablespaceInfo = tablespaceInfoDAO.findById(id);
        if (force && tablespaceInfo == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return tablespaceInfo;
    }


    /**
     * 查询【表空间】详情
     *
     * @param id
     * @return
     */
    public TablespaceInfoShowVO show(Integer id) {
        TablespaceInfoPO tablespaceInfo = this.getTablespaceInfo(id, true);
        TablespaceInfoShowVO showVO = TablespaceInfoMapper.INSTANCE.toShowVO(tablespaceInfo);
        return showVO;
    }

    /**
     * 删除【表空间】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(Integer... ids) {
        int count = 0;
        for (Integer id : ids) {
            this.checkDeleteByTablestructureInfo(id);
            // 校验是否存在【应用】关联
            this.checkDeleteByAppInfo(id);
            count += tablespaceInfoDAO.delete(id);
        }
        return count;
    }

    /**
     * 校验是否存在【表结构】关联
     *
     * @param id
     */
    private void checkDeleteByTablestructureInfo(Integer id) {
        int count = tablestructureInfoDAO.getCountByTablespaceId(id);
        if(count>0){
            throw new BusinessException(ErrorCode.CASCADE_DELETE_ERROR);
        }
    }

    /**
     * 校验是否存在【应用】关联
     *
     * @param id
     */
    private void checkDeleteByAppInfo(Integer id) {
        int count = appInfoDAO.getCountByTablespaceInfo(id);
        if(count>0){
            throw new BusinessException(ErrorCode.CASCADE_DELETE_ERROR);
        }
    }


}


