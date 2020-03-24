package io.nuls.data.service;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.dao.SystemDictDAO;
import io.nuls.data.pojo.dto.SystemDictAddDTO;
import io.nuls.data.pojo.dto.SystemDictUpdateDTO;
import io.nuls.data.pojo.mapper.SystemDictMapper;
import io.nuls.data.pojo.po.SystemDictPO;
import io.nuls.data.pojo.qo.SystemDictQO;
import io.nuls.data.pojo.vo.SystemDictListVO;
import io.nuls.data.pojo.vo.SystemDictShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【系统参数】增删改查服务
 *
 * @author Long
 * @date 2020/03/16
 */
@Service
public class SystemDictService {

    @Autowired
    private SystemDictDAO systemDictDAO;


    /**
     * 新增【系统参数】
     *
     * @param systemDictDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public SystemDictPO save(SystemDictAddDTO systemDictDTO) {
        SystemDictPO systemDict = SystemDictMapper.INSTANCE.fromAddDTO(systemDictDTO);
        systemDictDAO.save(systemDict);
        return systemDict;
    }

    /**
     * 修改【系统参数】
     *
     * @param systemDictUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public SystemDictPO update(SystemDictUpdateDTO systemDictUpdateDTO) {
        Integer id = systemDictUpdateDTO.getId();
        SystemDictPO systemDict = this.getSystemDict(id, true);
        SystemDictMapper.INSTANCE.setUpdateDTO(systemDict, systemDictUpdateDTO);
        systemDictDAO.update(systemDict);
        return systemDict;
    }

    /**
     * 查询分页列表
     *
     * @param systemDictQO
     * @return
     */
    public PageVO<SystemDictListVO> list(SystemDictQO systemDictQO) {
        PageVO<SystemDictListVO> page = systemDictDAO.findByPage(systemDictQO);
        return page;
    }

    /**
     * 根据主键获取【系统参数】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public SystemDictPO getSystemDict(Integer id, boolean force){
        SystemDictPO systemDict = systemDictDAO.findById(id);
        if (force && systemDict == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return systemDict;
    }


    /**
     * 查询【系统参数】详情
     *
     * @param id
     * @return
     */
    public SystemDictShowVO show(Integer id) {
        SystemDictPO systemDict = this.getSystemDict(id, true);
        SystemDictShowVO showVO = SystemDictMapper.INSTANCE.toShowVO(systemDict);
        return showVO;
    }

    /**
     * 删除【系统参数】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(Integer... ids) {
        int count = 0;
        for (Integer id : ids) {
            count += systemDictDAO.delete(id);
        }
        return count;
    }


}


