package io.nuls.data.service;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.dao.OperateLogDAO;
import io.nuls.data.pojo.dto.OperateLogAddDTO;
import io.nuls.data.pojo.dto.OperateLogUpdateDTO;
import io.nuls.data.pojo.mapper.OperateLogMapper;
import io.nuls.data.pojo.po.OperateLogPO;
import io.nuls.data.pojo.qo.OperateLogQO;
import io.nuls.data.pojo.vo.OperateLogListVO;
import io.nuls.data.pojo.vo.OperateLogShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【操作日志】增删改查服务
 *
 * @author Long
 * @date 2020/03/16
 */
@Service
public class OperateLogService {

    @Autowired
    private OperateLogDAO operateLogDAO;


    /**
     * 新增【操作日志】
     *
     * @param operateLogDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public OperateLogPO save(OperateLogAddDTO operateLogDTO) {
        OperateLogPO operateLog = OperateLogMapper.INSTANCE.fromAddDTO(operateLogDTO);
        operateLogDAO.save(operateLog);
        return operateLog;
    }

    /**
     * 修改【操作日志】
     *
     * @param operateLogUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public OperateLogPO update(OperateLogUpdateDTO operateLogUpdateDTO) {
        String id = operateLogUpdateDTO.getId();
        OperateLogPO operateLog = this.getOperateLog(id, true);
        OperateLogMapper.INSTANCE.setUpdateDTO(operateLog, operateLogUpdateDTO);
        operateLogDAO.update(operateLog);
        return operateLog;
    }

    /**
     * 查询分页列表
     *
     * @param operateLogQO
     * @return
     */
    public PageVO<OperateLogListVO> list(OperateLogQO operateLogQO) {
        PageVO<OperateLogListVO> page = operateLogDAO.findByPage(operateLogQO);
        return page;
    }

    /**
     * 根据主键获取【操作日志】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public OperateLogPO getOperateLog(String id, boolean force){
        OperateLogPO operateLog = operateLogDAO.findById(id);
        if (force && operateLog == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return operateLog;
    }


    /**
     * 查询【操作日志】详情
     *
     * @param id
     * @return
     */
    public OperateLogShowVO show(String id) {
        OperateLogPO operateLog = this.getOperateLog(id, true);
        OperateLogShowVO showVO = OperateLogMapper.INSTANCE.toShowVO(operateLog);
        return showVO;
    }

    /**
     * 删除【操作日志】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            count += operateLogDAO.delete(id);
        }
        return count;
    }


}


