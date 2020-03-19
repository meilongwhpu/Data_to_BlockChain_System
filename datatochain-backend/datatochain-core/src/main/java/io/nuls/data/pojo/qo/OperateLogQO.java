package io.nuls.data.pojo.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.nuls.common.constant.JsonFieldConst;
import io.nuls.common.pojo.qo.PageQO;
import io.swagger.annotations.ApiParam;

import java.util.Date;

import static io.nuls.data.pojo.example.OperateLogExample.*;

/**
 * 查询【操作日志】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
public class OperateLogQO extends PageQO {

    @ApiParam(value = N_OPER_TIME,example = E_OPER_TIME)
    @JsonFormat(pattern=JsonFieldConst.DEFAULT_DATE_FORMAT,timezone="GMT+8")
    private Date operTimeStart;

    @ApiParam(value = N_OPER_TIME,example = E_OPER_TIME)
    @JsonFormat(pattern=JsonFieldConst.DEFAULT_DATE_FORMAT,timezone="GMT+8")
    private Date operTimeEnd;


    public Date getOperTimeStart() {
        return this.operTimeStart;
    }

    public void setOperTimeStart(Date operTimeStart) {
        this.operTimeStart = operTimeStart;
    }

    public Date getOperTimeEnd() {
        return this.operTimeEnd;
    }

    public void setOperTimeEnd(Date operTimeEnd) {
        this.operTimeEnd = operTimeEnd;
    }

}

