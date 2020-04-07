
package io.nuls.data.pojo.dto;

import io.nuls.common.pojo.dto.AbstractDTO;
import io.nuls.common.validator.Const;
import io.nuls.data.constant.AllowNull;
import io.nuls.data.constant.FieldType;
import io.nuls.data.constant.IsKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.util.Map;

import static io.nuls.data.pojo.example.TablefieldInfoExample.*;

/**
 * 新增【表字段】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "新增【表数据】的参数")
public class TableDataAddDTO extends AbstractDTO {

    private Integer tableId;
    private Map<String,String> dataValues;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Map<String, String> getDataValues() {
        return dataValues;
    }

    public void setDataValues(Map<String, String> dataValues) {
        this.dataValues = dataValues;
    }
}


