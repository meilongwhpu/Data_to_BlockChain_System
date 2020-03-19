package io.nuls.data.help;

import io.nuls.common.util.DateUtil;
import io.nuls.data.pojo.dto.*;
import io.nuls.data.pojo.po.*;
import io.nuls.data.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.nuls.data.pojo.example.AppInfoExample.*;

@Component
public class AppInfoHelper {

    @Autowired
    private AppInfoService appInfoService;

    /**
     * 生成add测试数据
     * @return
     */
    public AppInfoAddDTO getAppInfoAddDTO(){
        AppInfoAddDTO dto = new AppInfoAddDTO();
        dto.setAppDesc(E_APP_DESC);
        dto.setAppKey(E_APP_KEY);
        dto.setCreateTime(DateUtil.parseDate(E_CREATE_TIME));
        dto.setUpdateTime(DateUtil.parseDate(E_UPDATE_TIME));
        return dto;
    }


    /**
     * 生成update测试数据
     * @return
     */
    public AppInfoUpdateDTO getAppInfoUpdateDTO(AppInfoPO appInfo){
        AppInfoUpdateDTO dto = new AppInfoUpdateDTO();
        dto.setId(appInfo.getId());
        dto.setAppDesc(appInfo.getAppDesc());
        dto.setAppKey(appInfo.getAppKey());
        dto.setCreateTime(appInfo.getCreateTime());
        dto.setUpdateTime(appInfo.getUpdateTime());
        return dto;
    }

    /**
     * 保存示例
     * @return
     */
    public AppInfoPO saveAppInfoExample(){
        AppInfoAddDTO addDTO = this.getAppInfoAddDTO();
        return appInfoService.save(addDTO);
    }



}

