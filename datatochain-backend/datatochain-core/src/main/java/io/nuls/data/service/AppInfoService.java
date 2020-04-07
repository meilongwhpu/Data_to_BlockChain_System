package io.nuls.data.service;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.qo.OptionQO;
import io.nuls.common.pojo.vo.OptionVO;
import io.nuls.common.pojo.vo.PageVO;
import io.nuls.data.dao.AppInfoDAO;
import io.nuls.data.dao.TablespaceInfoDAO;
import io.nuls.data.pojo.dto.AppInfoAddDTO;
import io.nuls.data.pojo.dto.AppInfoUpdateDTO;
import io.nuls.data.pojo.mapper.AppInfoMapper;
import io.nuls.data.pojo.po.AppInfoPO;
import io.nuls.data.pojo.qo.AppInfoQO;
import io.nuls.data.pojo.vo.AppInfoListVO;
import io.nuls.data.pojo.vo.AppInfoShowVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 【应用】增删改查服务
 *
 * @author Long
 * @date 2020/03/16
 */
@Service
public class AppInfoService {

    @Autowired
    private AppInfoDAO appInfoDAO;
    @Autowired
    private TablespaceInfoDAO tablespaceInfoDAO;


    /**
     * 新增【应用】
     *
     * @param appInfoDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public AppInfoPO save(AppInfoAddDTO appInfoDTO) {
        AppInfoPO appInfo = AppInfoMapper.INSTANCE.fromAddDTO(appInfoDTO);
        appInfo.setAppName("");
        appInfo.setCreatorId("");
        appInfoDAO.save(appInfo);
        return appInfo;
    }

    /**
     * 修改【应用】
     *
     * @param appInfoUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public AppInfoPO update(AppInfoUpdateDTO appInfoUpdateDTO) {
        Integer id = appInfoUpdateDTO.getId();
        AppInfoPO appInfo = this.getAppInfo(id, true);
        AppInfoMapper.INSTANCE.setUpdateDTO(appInfo, appInfoUpdateDTO);
        appInfoDAO.update(appInfo);
        return appInfo;
    }

    /**
     * 查询分页列表
     *
     * @param appInfoQO
     * @return
     */
    public PageVO<AppInfoListVO> list(AppInfoQO appInfoQO) {
        PageVO<AppInfoListVO> page = appInfoDAO.findByPage(appInfoQO);
        return page;
    }

    /**
     * 查询【应用】选项列表
     *
     * @return
     */
    public List<OptionVO<Integer, String>> findOptions(OptionQO<Integer, String> qo) {
        List<OptionVO<Integer, String>> options = appInfoDAO.findOptions(qo);
        return options;
    }

    /**
     * 根据主键获取【应用】
     * 不获取多对多级联对象
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public AppInfoPO getAppInfo(Integer id, boolean force){
        return this.getAppInfo(id, false, force);
    }

    /**
     * 根据主键获取【应用】
     *
     * @param id 主键
     * @param withTablespaceInfo 是否级联获取【表空间】
     * @param force 是否强制获取
     * @return
     */
    public AppInfoPO getAppInfo(Integer id, boolean withTablespaceInfo, boolean force){
        AppInfoPO appInfo = appInfoDAO.findById(id);
        if (force && appInfo == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        if (withTablespaceInfo){
            appInfo.setTablespaceInfoPOList(tablespaceInfoDAO.findByAppInfo(id));
        }
        return appInfo;
    }


    /**
     * 查询【应用】详情
     *
     * @param id
     * @return
     */
    public AppInfoShowVO show(Integer id) {
        AppInfoPO appInfo = this.getAppInfo(id, true);
        AppInfoShowVO showVO = AppInfoMapper.INSTANCE.toShowVO(appInfo);
        return showVO;
    }

    /**
     * 删除【应用】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(Integer... ids) {
        int count = 0;
        for (Integer id : ids) {
            count += appInfoDAO.delete(id);
        }
        return count;
    }

    /**
     * 执行【表空间】添加
     *
     * @param id
     * @param tablespaceId
     * @return
     */
    private int doAddTablespaceInfo(Integer id, Integer... tablespaceId) {
        int count = 0;
        for (Integer _id : tablespaceId) {
            if(tablespaceInfoDAO.exist(_id)){
                count += appInfoDAO.addTablespaceInfo(id, _id);
            }
        }
        return count;
    }

    /**
     * 添加【表空间】
     *
     * @param id
     * @param tablespaceId
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int addTablespaceInfo(Integer id, Integer... tablespaceId) {
        AppInfoPO appInfo = this.getAppInfo(id, true);
        if(ArrayUtils.isEmpty(tablespaceId)){
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        return doAddTablespaceInfo(id, tablespaceId);
    }

    /**
     * 移除【表空间】
     *
     * @param id
     * @param tablespaceId
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int removeTablespaceInfo(Integer id, Integer... tablespaceId) {
        AppInfoPO appInfo = this.getAppInfo(id, true);
        if(ArrayUtils.isEmpty(tablespaceId)){
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        return appInfoDAO.removeTablespaceInfo(id, tablespaceId);
    }


}


