package io.nuls.data.pojo.mapper;

import io.nuls.data.pojo.dto.TablefieldInfoAddDTO;
import io.nuls.data.pojo.dto.TablefieldInfoUpdateDTO;
import io.nuls.data.pojo.po.TablefieldInfoPO;
import io.nuls.data.pojo.vo.TablefieldInfoShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【表字段】映射
 *
 * @author Long
 * @date 2020/03/16
 */
@Mapper
public interface TablefieldInfoMapper {

    TablefieldInfoMapper INSTANCE = Mappers.getMapper( TablefieldInfoMapper.class );

    /**
     * addDTO映射po
     *
     * @param tablefieldInfoAddDTO
     * @return
     */
    TablefieldInfoPO fromAddDTO(TablefieldInfoAddDTO tablefieldInfoAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param tablefieldInfoPO
     * @param tablefieldInfoUpdateDTO
     */
    void setUpdateDTO(@MappingTarget TablefieldInfoPO tablefieldInfoPO, TablefieldInfoUpdateDTO tablefieldInfoUpdateDTO);

    /**
     * po映射showVO
     *
     * @param tablefieldInfoPO
     * @return
     */
    TablefieldInfoShowVO toShowVO(TablefieldInfoPO tablefieldInfoPO);


}

