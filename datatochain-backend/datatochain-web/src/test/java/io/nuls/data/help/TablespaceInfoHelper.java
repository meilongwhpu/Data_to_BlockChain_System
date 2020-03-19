package io.nuls.data.help;

import io.nuls.common.util.DateUtil;
import io.nuls.common.util.SafeUtil;
import io.nuls.data.pojo.dto.*;
import io.nuls.data.pojo.po.*;
import io.nuls.data.service.TablespaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.nuls.data.pojo.example.TablespaceInfoExample.*;

@Component
public class TablespaceInfoHelper {

    @Autowired
    private TablespaceInfoService tablespaceInfoService;

    /**
     * 生成add测试数据
     * @return
     */
    public TablespaceInfoAddDTO getTablespaceInfoAddDTO(){
        TablespaceInfoAddDTO dto = new TablespaceInfoAddDTO();
        dto.setTablespaceName(E_TABLESPACE_NAME);
        dto.setTablespaceDesc(E_TABLESPACE_DESC);
        dto.setCodeMode(SafeUtil.getInteger(E_CODE_MODE));
        dto.setEncryptionMode(SafeUtil.getInteger(E_ENCRYPTION_MODE));
        dto.setToChain(SafeUtil.getInteger(E_TO_CHAIN));
        dto.setCreateTime(DateUtil.parseDate(E_CREATE_TIME));
        dto.setCreatorId(E_CREATOR_ID);
        return dto;
    }


    /**
     * 生成update测试数据
     * @return
     */
    public TablespaceInfoUpdateDTO getTablespaceInfoUpdateDTO(TablespaceInfoPO tablespaceInfo){
        TablespaceInfoUpdateDTO dto = new TablespaceInfoUpdateDTO();
        dto.setId(tablespaceInfo.getId());
        dto.setTablespaceName(tablespaceInfo.getTablespaceName());
        dto.setTablespaceDesc(tablespaceInfo.getTablespaceDesc());
        dto.setCodeMode(tablespaceInfo.getCodeMode());
        dto.setEncryptionMode(tablespaceInfo.getEncryptionMode());
        dto.setToChain(tablespaceInfo.getToChain());
        dto.setCreateTime(tablespaceInfo.getCreateTime());
        dto.setCreatorId(tablespaceInfo.getCreatorId());
        return dto;
    }

    /**
     * 保存示例
     * @return
     */
    public TablespaceInfoPO saveTablespaceInfoExample(){
        TablespaceInfoAddDTO addDTO = this.getTablespaceInfoAddDTO();
        return tablespaceInfoService.save(addDTO);
    }



}

