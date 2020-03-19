package io.nuls.data.pojo.mapper;

import io.nuls.data.pojo.dto.SystemDictAddDTO;
import io.nuls.data.pojo.dto.SystemDictUpdateDTO;
import io.nuls.data.pojo.po.SystemDictPO;
import io.nuls.data.pojo.vo.SystemDictShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【系统参数】映射
 *
 * @author Long
 * @date 2020/03/16
 */
@Mapper
public interface SystemDictMapper {

    SystemDictMapper INSTANCE = Mappers.getMapper( SystemDictMapper.class );

    /**
     * addDTO映射po
     *
     * @param systemDictAddDTO
     * @return
     */
    SystemDictPO fromAddDTO(SystemDictAddDTO systemDictAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param systemDictPO
     * @param systemDictUpdateDTO
     */
    void setUpdateDTO(@MappingTarget SystemDictPO systemDictPO, SystemDictUpdateDTO systemDictUpdateDTO);

    /**
     * po映射showVO
     *
     * @param systemDictPO
     * @return
     */
    SystemDictShowVO toShowVO(SystemDictPO systemDictPO);


}

