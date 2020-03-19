package io.nuls.data.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.nuls.common.constant.JsonFieldConst;
import io.nuls.common.pojo.vo.AbstractVO;
import io.nuls.data.pojo.example.TablespaceInfoExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import static io.nuls.data.pojo.example.TablestructureInfoExample.*;

/**
 * 【表结构】列表展示对象
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "【表结构】列表展示对象")
public class TablestructureInfoListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_TABLE_NAME, example = E_TABLE_NAME)
    private String tableName;

    @ApiModelProperty(notes = N_TABLE_DESC, example = E_TABLE_DESC)
    private String tableDesc;

    @ApiModelProperty(notes = N_CREATE_TIME, example = E_CREATE_TIME)
    @JsonFormat(pattern = JsonFieldConst.DEFAULT_DATETIME_FORMAT, timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(notes = N_UPDATE_TIME, example = E_UPDATE_TIME)
    @JsonFormat(pattern = JsonFieldConst.DEFAULT_DATETIME_FORMAT, timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(notes = N_CREATOR_ID, example = E_CREATOR_ID)
    private String creatorId;

    @ApiModelProperty(notes = TablespaceInfoExample.N_TABLESPACE_NAME, example = TablespaceInfoExample.E_TABLESPACE_NAME)
    private String tablespaceName;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return this.tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
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

    public String getTablespaceName() {
        return this.tablespaceName;
    }

    public void setTablespaceName(String tablespaceName) {
        this.tablespaceName = tablespaceName;
    }



}

