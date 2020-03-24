package io.nuls.data.pojo.po;

import io.nuls.common.pojo.po.AbstractPO;

import java.util.Date;

/**
 * 操作日志
 *
 * @author Long
 * @date 2020/03/16
 */
public class OperateLogPO extends AbstractPO {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 操作时间
     */
    private Date operTime;

    /**
     * 操作科目
     */
    private String operSubject;

    /**
     * 操作明细
     */
    private String operDetail;

    /**
     * 客户端IP地址
     */
    private String operIp;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

