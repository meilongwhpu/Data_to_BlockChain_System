package io.nuls.data.service;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.dao.TablefieldInfoDAO;
import io.nuls.data.dao.TablespaceInfoDAO;
import io.nuls.data.dao.TablestructureInfoDAO;
import io.nuls.data.pojo.dto.TablestructureInfoAddDTO;
import io.nuls.data.pojo.dto.TablestructureInfoUpdateDTO;
import io.nuls.data.pojo.mapper.TablestructureInfoMapper;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.po.TablestructureInfoPO;
import io.nuls.data.pojo.qo.TablestructureInfoQO;
import io.nuls.data.pojo.vo.TablestructureInfoListVO;
import io.nuls.data.pojo.vo.TablestructureInfoShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 【表结构】增删改查服务
 *
 * @author Long
 * @date 2020/03/16
 */
@Service
public class TablestructureInfoService {

    @Autowired
    private TablefieldInfoDAO tablefieldInfoDAO;
    @Autowired
    private TablespaceInfoDAO tablespaceInfoDAO;
    @Autowired
    private TablestructureInfoDAO tablestructureInfoDAO;


    /**
     * 新增【表结构】
     *
     * @param tablestructureInfoDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TablestructureInfoPO save(TablestructureInfoAddDTO tablestructureInfoDTO) {
        TablestructureInfoPO tablestructureInfo = TablestructureInfoMapper.INSTANCE.fromAddDTO(tablestructureInfoDTO);
        if(tablestructureInfo.getTablespaceId() != null){
            Assert.isTrue(tablespaceInfoDAO.exist(tablestructureInfo.getTablespaceId()),"表空间名称有误");
        }
        tablestructureInfo.setCreateTime(new Date());
        tablestructureInfoDAO.save(tablestructureInfo);
        return tablestructureInfo;
    }

    /**
     * 修改【表结构】
     *
     * @param tablestructureInfoUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TablestructureInfoPO update(TablestructureInfoUpdateDTO tablestructureInfoUpdateDTO) {
        Integer id = tablestructureInfoUpdateDTO.getId();
        TablestructureInfoPO tablestructureInfo = this.getTablestructureInfo(id, true);
        TablestructureInfoMapper.INSTANCE.setUpdateDTO(tablestructureInfo, tablestructureInfoUpdateDTO);
        tablestructureInfoDAO.update(tablestructureInfo);
        return tablestructureInfo;
    }

    /**
     * 查询分页列表
     *
     * @param tablestructureInfoQO
     * @return
     */
    public PageVO<TablestructureInfoListVO> list(TablestructureInfoQO tablestructureInfoQO) {
        PageVO<TablestructureInfoListVO> page = tablestructureInfoDAO.findByPage(tablestructureInfoQO);
        return page;
    }

    /**
     * 查询【表结构】选项列表
     *
     * @return
     */
    public List<OptionVO<Integer, String>> findOptions(OptionQO<Integer, String> qo) {
        List<OptionVO<Integer, String>> options = tablestructureInfoDAO.findOptions(qo);
        return options;
    }

    /**
     * 根据主键获取【表结构】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public TablestructureInfoPO getTablestructureInfo(Integer id, boolean force){
        TablestructureInfoPO tablestructureInfo = tablestructureInfoDAO.findById(id);
        if (force && tablestructureInfo == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return tablestructureInfo;
    }


    /**
     * 查询【表结构】详情
     *
     * @param id
     * @return
     */
    public TablestructureInfoShowVO show(Integer id) {
        TablestructureInfoPO tablestructureInfo = this.getTablestructureInfo(id, true);
        TablestructureInfoShowVO showVO = TablestructureInfoMapper.INSTANCE.toShowVO(tablestructureInfo);
        if(tablestructureInfo.getTablespaceId()!=null){
            TablespaceInfoPO _tablespaceInfoPO = tablespaceInfoDAO.findById(tablestructureInfo.getTablespaceId());
            showVO.setTablespaceName(_tablespaceInfoPO.getTablespaceName());
        }
        return showVO;
    }

    /**
     * 删除【表结构】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(Integer... ids) {
        int count = 0;
        for (Integer id : ids) {
            this.checkDeleteByTablefieldInfo(id);
            count += tablestructureInfoDAO.delete(id);
        }
        return count;
    }

    /**
     * 校验是否存在【表字段】关联
     *
     * @param id
     */
    private void checkDeleteByTablefieldInfo(Integer id) {
        int count = tablefieldInfoDAO.getCountByTableId(id);
        if(count>0){
            throw new BusinessException(ErrorCode.CASCADE_DELETE_ERROR);
        }
    }


}


