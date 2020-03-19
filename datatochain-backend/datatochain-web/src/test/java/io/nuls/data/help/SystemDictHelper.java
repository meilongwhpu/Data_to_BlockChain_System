package io.nuls.data.help;

import io.nuls.data.pojo.dto.*;
import io.nuls.data.pojo.po.*;
import io.nuls.data.service.SystemDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.nuls.data.pojo.example.SystemDictExample.*;

@Component
public class SystemDictHelper {

    @Autowired
    private SystemDictService systemDictService;

    /**
     * 生成add测试数据
     * @return
     */
    public SystemDictAddDTO getSystemDictAddDTO(){
        SystemDictAddDTO dto = new SystemDictAddDTO();
        dto.setDictKey(E_DICT_KEY);
        dto.setDictValue(E_DICT_VALUE);
        dto.setDictDesc(E_DICT_DESC);
        return dto;
    }


    /**
     * 生成update测试数据
     * @return
     */
    public SystemDictUpdateDTO getSystemDictUpdateDTO(SystemDictPO systemDict){
        SystemDictUpdateDTO dto = new SystemDictUpdateDTO();
        dto.setId(systemDict.getId());
        dto.setDictKey(systemDict.getDictKey());
        dto.setDictValue(systemDict.getDictValue());
        dto.setDictDesc(systemDict.getDictDesc());
        return dto;
    }

    /**
     * 保存示例
     * @return
     */
    public SystemDictPO saveSystemDictExample(){
        SystemDictAddDTO addDTO = this.getSystemDictAddDTO();
        return systemDictService.save(addDTO);
    }



}

