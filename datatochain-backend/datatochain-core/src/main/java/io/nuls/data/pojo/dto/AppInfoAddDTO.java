
package io.nuls.data.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.nuls.common.constant.JsonFieldConst;
import io.nuls.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static io.nuls.data.pojo.example.AppInfoExample.*;

/**
 * 新增【应用】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "新增【应用】的参数")
public class AppInfoAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_APP_DESC, example = E_APP_DESC)
    @Length(max = 50)
    private String appDesc;

    @ApiModelProperty(notes = N_APP_KEY, example = E_APP_KEY, required = true)
    @NotNull
    @Length(max = 50)
    private String appKey;

    @ApiModelProperty(notes = N_CREATE_TIME, example = E_CREATE_TIME)
    @JsonFormat(pattern=JsonFieldConst.DEFAULT_DATE_FORMAT,timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(notes = N_UPDATE_TIME, example = E_UPDATE_TIME)
    @JsonFormat(pattern=JsonFieldConst.DEFAULT_DATE_FORMAT,timezone="GMT+8")
    private Date updateTime;


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

}


