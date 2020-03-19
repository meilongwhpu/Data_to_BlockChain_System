package io.nuls.data.pojo.mapper;

import io.nuls.data.pojo.dto.TablespaceInfoAddDTO;
import io.nuls.data.pojo.dto.TablespaceInfoUpdateDTO;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.vo.TablespaceInfoListVO;
import io.nuls.data.pojo.vo.TablespaceInfoShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 【表空间】映射
 *
 * @author Long
 * @date 2020/03/16
 */
@Mapper
public interface TablespaceInfoMapper {

    TablespaceInfoMapper INSTANCE = Mappers.getMapper( TablespaceInfoMapper.class );

    /**
     * addDTO映射po
     *
     * @param tablespaceInfoAddDTO
     * @return
     */
    TablespaceInfoPO fromAddDTO(TablespaceInfoAddDTO tablespaceInfoAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param tablespaceInfoPO
     * @param tablespaceInfoUpdateDTO
     */
    void setUpdateDTO(@MappingTarget TablespaceInfoPO tablespaceInfoPO, TablespaceInfoUpdateDTO tablespaceInfoUpdateDTO);

    /**
     * po映射showVO
     *
     * @param tablespaceInfoPO
     * @return
     */
    TablespaceInfoShowVO toShowVO(TablespaceInfoPO tablespaceInfoPO);


    List<TablespaceInfoListVO> toListVOList(List<TablespaceInfoPO> list);

}

