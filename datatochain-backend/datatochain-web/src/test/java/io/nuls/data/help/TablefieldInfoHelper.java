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
    public TablefieldInfoAddDTO getTablefieldInfoAddDTO(Integer tableId){
        TablefieldInfoAddDTO dto = new TablefieldInfoAddDTO();
        dto.setTableId(tableId);
        dto.setFieldName(E_FIELD_NAME);
        dto.setFieldDesc(E_FIELD_DESC);
        dto.setFieldType(SafeUtil.getInteger(E_FIELD_TYPE));
        dto.setFieldLength(SafeUtil.getInteger(E_FIELD_LENGTH));
        dto.setFieldDecimalLength(SafeUtil.getInteger(E_FIELD_DECIMAL_LENGTH));
        dto.setFieldIsKey(SafeUtil.getBoolean(E_FIELD_IS_KEY));
        dto.setAllowNull(SafeUtil.getInteger(E_ALLOW_NULL));
        dto.setFieldDefaultValue(E_FIELD_DEFAULT_VALUE);
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
        dto.setFieldDecimalLength(tablefieldInfo.getFieldDecimalLength());
        dto.setFieldIsKey(tablefieldInfo.getFieldIsKey());
        dto.setAllowNull(tablefieldInfo.getAllowNull());
        dto.setFieldDefaultValue(tablefieldInfo.getFieldDefaultValue());
        dto.setCreatorId(tablefieldInfo.getCreatorId());
        return dto;
    }

    /**
     * 保存示例
     * @return
     */
    public TablefieldInfoPO saveTablefieldInfoExample(Integer tableId){
        TablefieldInfoAddDTO addDTO = this.getTablefieldInfoAddDTO(tableId);
        return tablefieldInfoService.save(addDTO);
    }



}

