package io.nuls.data.pojo.mapper;

import io.nuls.data.pojo.dto.AppInfoAddDTO;
import io.nuls.data.pojo.dto.AppInfoUpdateDTO;
import io.nuls.data.pojo.po.AppInfoPO;
import io.nuls.data.pojo.vo.AppInfoShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【应用】映射
 *
 * @author Long
 * @date 2020/03/16
 */
@Mapper
public interface AppInfoMapper {

    AppInfoMapper INSTANCE = Mappers.getMapper( AppInfoMapper.class );

    /**
     * addDTO映射po
     *
     * @param appInfoAddDTO
     * @return
     */
    AppInfoPO fromAddDTO(AppInfoAddDTO appInfoAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param appInfoPO
     * @param appInfoUpdateDTO
     */
    void setUpdateDTO(@MappingTarget AppInfoPO appInfoPO, AppInfoUpdateDTO appInfoUpdateDTO);

    /**
     * po映射showVO
     *
     * @param appInfoPO
     * @return
     */
    AppInfoShowVO toShowVO(AppInfoPO appInfoPO);


}

