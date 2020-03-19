package io.nuls.data.pojo.qo;

import io.nuls.common.pojo.qo.PageQO;
import io.nuls.data.pojo.example.TablestructureInfoExample;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;

import static io.nuls.data.pojo.example.TablefieldInfoExample.*;

/**
 * 查询【表字段】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
public class TablefieldInfoQO extends PageQO {

    @ApiParam(value = TablestructureInfoExample.N_TABLE_NAME,example = TablestructureInfoExample.E_TABLE_NAME)
    @Length(max = 50,message = "tableName最大长度不能超过{max}")
    private String tableName;


    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}

