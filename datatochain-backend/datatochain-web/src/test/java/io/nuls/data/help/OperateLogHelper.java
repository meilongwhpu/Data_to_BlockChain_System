package io.nuls.data.help;

import io.nuls.data.pojo.dto.*;
import io.nuls.data.pojo.po.*;
import io.nuls.data.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.nuls.data.pojo.example.OperateLogExample.*;

@Component
public class OperateLogHelper {

    @Autowired
    private OperateLogService operateLogService;

    /**
     * 生成add测试数据
     * @return
     */
    public OperateLogAddDTO getOperateLogAddDTO(){
        OperateLogAddDTO dto = new OperateLogAddDTO();
        return dto;
    }


    /**
     * 生成update测试数据
     * @return
     */
    public OperateLogUpdateDTO getOperateLogUpdateDTO(OperateLogPO operateLog){
        OperateLogUpdateDTO dto = new OperateLogUpdateDTO();
        dto.setId(operateLog.getId());
        return dto;
    }

    /**
     * 保存示例
     * @return
     */
    public OperateLogPO saveOperateLogExample(){
        OperateLogAddDTO addDTO = this.getOperateLogAddDTO();
        return operateLogService.save(addDTO);
    }



}

