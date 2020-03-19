package io.nuls.data.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.nuls.common.constant.JsonFieldConst;
import io.nuls.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import static io.nuls.data.pojo.example.AppInfoExample.*;

/**
 * 【应用】列表展示对象
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "【应用】列表展示对象")
public class AppInfoListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_APP_NAME, example = E_APP_NAME)
    private String appName;

    @ApiModelProperty(notes = N_APP_DESC, example = E_APP_DESC)
    private String appDesc;

    @ApiModelProperty(notes = N_APP_KEY, example = E_APP_KEY)
    private String appKey;

    @ApiModelProperty(notes = N_CREATE_TIME, example = E_CREATE_TIME)
    @JsonFormat(pattern = JsonFieldConst.DEFAULT_DATE_FORMAT, timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(notes = N_UPDATE_TIME, example = E_UPDATE_TIME)
    @JsonFormat(pattern = JsonFieldConst.DEFAULT_DATE_FORMAT, timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(notes = N_CREATOR_ID, example = E_CREATOR_ID)
    private String creatorId;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return this.appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }



}

