package io.nuls.data.pojo.mapper;

import io.nuls.data.pojo.dto.OperateLogAddDTO;
import io.nuls.data.pojo.dto.OperateLogUpdateDTO;
import io.nuls.data.pojo.po.OperateLogPO;
import io.nuls.data.pojo.vo.OperateLogShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【操作日志】映射
 *
 * @author Long
 * @date 2020/03/16
 */
@Mapper
public interface OperateLogMapper {

    OperateLogMapper INSTANCE = Mappers.getMapper( OperateLogMapper.class );

    /**
     * addDTO映射po
     *
     * @param operateLogAddDTO
     * @return
     */
    OperateLogPO fromAddDTO(OperateLogAddDTO operateLogAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param operateLogPO
     * @param operateLogUpdateDTO
     */
    void setUpdateDTO(@MappingTarget OperateLogPO operateLogPO, OperateLogUpdateDTO operateLogUpdateDTO);

    /**
     * po映射showVO
     *
     * @param operateLogPO
     * @return
     */
    OperateLogShowVO toShowVO(OperateLogPO operateLogPO);


}

