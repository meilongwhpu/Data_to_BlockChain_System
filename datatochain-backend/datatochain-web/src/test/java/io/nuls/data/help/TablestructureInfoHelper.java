package io.nuls.data.help;

import io.nuls.common.util.SafeUtil;
import io.nuls.data.pojo.dto.*;
import io.nuls.data.pojo.po.*;
import io.nuls.data.service.TablestructureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.nuls.data.pojo.example.TablestructureInfoExample.*;

@Component
public class TablestructureInfoHelper {

    @Autowired
    private TablestructureInfoService tablestructureInfoService;

    /**
     * 生成add测试数据
     * @return
     */
    public TablestructureInfoAddDTO getTablestructureInfoAddDTO(Integer tablespaceId){
        TablestructureInfoAddDTO dto = new TablestructureInfoAddDTO();
        dto.setTablespaceId(tablespaceId);
        dto.setTableName(E_TABLE_NAME);
        dto.setTableDesc(E_TABLE_DESC);
        dto.setCreatorId(E_CREATOR_ID);
        dto.setIsCreate(SafeUtil.getBoolean(E_IS_CREATE));
        return dto;
    }


    /**
     * 生成update测试数据
     * @return
     */
    public TablestructureInfoUpdateDTO getTablestructureInfoUpdateDTO(TablestructureInfoPO tablestructureInfo){
        TablestructureInfoUpdateDTO dto = new TablestructureInfoUpdateDTO();
        dto.setId(tablestructureInfo.getId());
        dto.setTableName(tablestructureInfo.getTableName());
        dto.setTableDesc(tablestructureInfo.getTableDesc());
        return dto;
    }

    /**
     * 保存示例
     * @return
     */
    public TablestructureInfoPO saveTablestructureInfoExample(Integer tablespaceId){
        TablestructureInfoAddDTO addDTO = this.getTablestructureInfoAddDTO(tablespaceId);
        return tablestructureInfoService.save(addDTO);
    }



}

