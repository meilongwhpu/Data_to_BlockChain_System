package io.nuls.data.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.nuls.common.constant.JsonFieldConst;
import io.nuls.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import static io.nuls.data.pojo.example.OperateLogExample.*;

/**
 * 【操作日志】列表展示对象
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "【操作日志】列表展示对象")
public class OperateLogListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_USER_ID, example = E_USER_ID)
    private String userId;

    @ApiModelProperty(notes = N_OPER_TIME, example = E_OPER_TIME)
    @JsonFormat(pattern = JsonFieldConst.DEFAULT_DATE_FORMAT, timezone = "GMT+8")
    private Date operTime;

    @ApiModelProperty(notes = N_OPER_SUBJECT, example = E_OPER_SUBJECT)
    private String operSubject;

    @ApiModelProperty(notes = N_OPER_DETAIL, example = E_OPER_DETAIL)
    private String operDetail;

    @ApiModelProperty(notes = N_OPER_IP, example = E_OPER_IP)
    private String operIp;

    @ApiModelProperty(notes = N_REMARK, example = E_REMARK)
    private String remark;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOperTime() {
        return this.operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getOperSubject() {
        return this.operSubject;
    }

    public void setOperSubject(String operSubject) {
        this.operSubject = operSubject;
    }

    public String getOperDetail() {
        return this.operDetail;
    }

    public void setOperDetail(String operDetail) {
        this.operDetail = operDetail;
    }

    public String getOperIp() {
        return this.operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



}

