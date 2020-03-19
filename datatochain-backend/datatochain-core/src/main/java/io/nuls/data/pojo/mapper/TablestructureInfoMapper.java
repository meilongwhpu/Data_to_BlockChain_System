package io.nuls.data.pojo.mapper;

import io.nuls.data.pojo.dto.TablestructureInfoAddDTO;
import io.nuls.data.pojo.dto.TablestructureInfoUpdateDTO;
import io.nuls.data.pojo.po.TablestructureInfoPO;
import io.nuls.data.pojo.vo.TablestructureInfoShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【表结构】映射
 *
 * @author Long
 * @date 2020/03/16
 */
@Mapper
public interface TablestructureInfoMapper {

    TablestructureInfoMapper INSTANCE = Mappers.getMapper( TablestructureInfoMapper.class );

    /**
     * addDTO映射po
     *
     * @param tablestructureInfoAddDTO
     * @return
     */
    TablestructureInfoPO fromAddDTO(TablestructureInfoAddDTO tablestructureInfoAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param tablestructureInfoPO
     * @param tablestructureInfoUpdateDTO
     */
    void setUpdateDTO(@MappingTarget TablestructureInfoPO tablestructureInfoPO, TablestructureInfoUpdateDTO tablestructureInfoUpdateDTO);

    /**
     * po映射showVO
     *
     * @param tablestructureInfoPO
     * @return
     */
    TablestructureInfoShowVO toShowVO(TablestructureInfoPO tablestructureInfoPO);


}

