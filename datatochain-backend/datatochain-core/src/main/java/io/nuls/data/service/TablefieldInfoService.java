package io.nuls.data.service;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.dao.TablefieldInfoDAO;
import io.nuls.data.dao.TablestructureInfoDAO;
import io.nuls.data.pojo.dto.TablefieldInfoAddDTO;
import io.nuls.data.pojo.dto.TablefieldInfoUpdateDTO;
import io.nuls.data.pojo.mapper.TablefieldInfoMapper;
import io.nuls.data.pojo.po.TablefieldInfoPO;
import io.nuls.data.pojo.po.TablestructureInfoPO;
import io.nuls.data.pojo.qo.TablefieldInfoQO;
import io.nuls.data.pojo.vo.TablefieldInfoListVO;
import io.nuls.data.pojo.vo.TablefieldInfoShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 【表字段】增删改查服务
 *
 * @author Long
 * @date 2020/03/16
 */
@Service
public class TablefieldInfoService {

    @Autowired
    private TablefieldInfoDAO tablefieldInfoDAO;
    @Autowired
    private TablestructureInfoDAO tablestructureInfoDAO;


    /**
     * 新增【表字段】
     *
     * @param tablefieldInfoDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TablefieldInfoPO save(TablefieldInfoAddDTO tablefieldInfoDTO) {
        TablefieldInfoPO tablefieldInfo = TablefieldInfoMapper.INSTANCE.fromAddDTO(tablefieldInfoDTO);
        if(tablefieldInfo.getTableId() != null){
            Assert.isTrue(tablestructureInfoDAO.exist(tablefieldInfo.getTableId()),"表结构名称有误");
        }
        tablefieldInfoDAO.save(tablefieldInfo);
        return tablefieldInfo;
    }

    /**
     * 修改【表字段】
     *
     * @param tablefieldInfoUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TablefieldInfoPO update(TablefieldInfoUpdateDTO tablefieldInfoUpdateDTO) {
        String id = tablefieldInfoUpdateDTO.getId();
        TablefieldInfoPO tablefieldInfo = this.getTablefieldInfo(id, true);
        TablefieldInfoMapper.INSTANCE.setUpdateDTO(tablefieldInfo, tablefieldInfoUpdateDTO);
        if(tablefieldInfo.getTableId() != null){
            Assert.isTrue(tablestructureInfoDAO.exist(tablefieldInfo.getTableId()),"表结构名称有误");
        }
        tablefieldInfoDAO.update(tablefieldInfo);
        return tablefieldInfo;
    }

    /**
     * 查询分页列表
     *
     * @param tablefieldInfoQO
     * @return
     */
    public PageVO<TablefieldInfoListVO> list(TablefieldInfoQO tablefieldInfoQO) {
        PageVO<TablefieldInfoListVO> page = tablefieldInfoDAO.findByPage(tablefieldInfoQO);
        return page;
    }

    /**
     * 根据主键获取【表字段】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public TablefieldInfoPO getTablefieldInfo(String id, boolean force){
        TablefieldInfoPO tablefieldInfo = tablefieldInfoDAO.findById(id);
        if (force && tablefieldInfo == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return tablefieldInfo;
    }


    /**
     * 查询【表字段】详情
     *
     * @param id
     * @return
     */
    public TablefieldInfoShowVO show(String id) {
        TablefieldInfoPO tablefieldInfo = this.getTablefieldInfo(id, true);
        TablefieldInfoShowVO showVO = TablefieldInfoMapper.INSTANCE.toShowVO(tablefieldInfo);
        if(tablefieldInfo.getTableId()!=null){
            TablestructureInfoPO _tablestructureInfoPO = tablestructureInfoDAO.findById(tablefieldInfo.getTableId());
            showVO.setTableName(_tablestructureInfoPO.getTableName());
        }
        return showVO;
    }

    /**
     * 删除【表字段】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            count += tablefieldInfoDAO.delete(id);
        }
        return count;
    }


}


