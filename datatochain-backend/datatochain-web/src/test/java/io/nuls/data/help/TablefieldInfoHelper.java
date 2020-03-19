package io.nuls.data.help;

import io.nuls.common.util.SafeUtil;
import io.nuls.data.pojo.dto.*;
import io.nuls.data.pojo.po.*;
import io.nuls.data.service.TablefieldInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.nuls.data.pojo.example.TablefieldInfoExample.*;

@Component
public class TablefieldInfoHelper {

    @Autowired
    private TablefieldInfoService tablefieldInfoService;

    /**
     * 生成add测试数据
     * @return
     */
    public TablefieldInfoAddDTO getTablefieldInfoAddDTO(String tableId){
        TablefieldInfoAddDTO dto = new TablefieldInfoAddDTO();
        dto.setTableId(tableId);
        dto.setFieldName(E_FIELD_NAME);
        dto.setFieldDesc(E_FIELD_DESC);
        dto.setFieldType(SafeUtil.getInteger(E_FIELD_TYPE));
        dto.setFieldLength(E_FIELD_LENGTH);
        dto.setAllowNull(SafeUtil.getInteger(E_ALLOW_NULL));
        dto.setCreatorId(E_CREATOR_ID);
        return dto;
    }


    /**
     * 生成update测试数据
     * @return
     */
    public TablefieldInfoUpdateDTO getTablefieldInfoUpdateDTO(TablefieldInfoPO tablefieldInfo){
        TablefieldInfoUpdateDTO dto = new TablefieldInfoUpdateDTO();
        dto.setId(tablefieldInfo.getId());
        dto.setTableId(tablefieldInfo.getTableId());
        dto.setFieldName(tablefieldInfo.getFieldName());
        dto.setFieldDesc(tablefieldInfo.getFieldDesc());
        dto.setFieldType(tablefieldInfo.getFieldType());
        dto.setFieldLength(tablefieldInfo.getFieldLength());
        dto.setAllowNull(tablefieldInfo.getAllowNull());
        dto.setCreatorId(tablefieldInfo.getCreatorId());
        return dto;
    }

    /**
     * 保存示例
     * @return
     */
    public TablefieldInfoPO saveTablefieldInfoExample(String tableId){
        TablefieldInfoAddDTO addDTO = this.getTablefieldInfoAddDTO(tableId);
        return tablefieldInfoService.save(addDTO);
    }



}

