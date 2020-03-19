package io.nuls.data.pojo.qo;

import io.nuls.common.pojo.qo.PageQO;
import io.nuls.data.pojo.example.TablespaceInfoExample;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;

import static io.nuls.data.pojo.example.TablestructureInfoExample.*;

/**
 * 查询【表结构】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
public class TablestructureInfoQO extends PageQO {

    @ApiParam(value = TablespaceInfoExample.N_TABLESPACE_NAME,example = TablespaceInfoExample.E_TABLESPACE_NAME)
    @Length(max = 50,message = "tablespaceName最大长度不能超过{max}")
    private String tablespaceName;


    public String getTablespaceName() {
        return this.tablespaceName;
    }

    public void setTablespaceName(String tablespaceName) {
        this.tablespaceName = tablespaceName;
    }

}

