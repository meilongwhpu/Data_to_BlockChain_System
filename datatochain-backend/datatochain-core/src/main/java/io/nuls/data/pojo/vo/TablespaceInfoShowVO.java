package io.nuls.data.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.nuls.common.constant.JsonFieldConst;
import io.nuls.common.pojo.vo.AbstractVO;
import io.nuls.data.constant.ChainType;
import io.nuls.data.constant.CodeMode;
import io.nuls.data.constant.EncryptionMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import static io.nuls.data.pojo.example.TablespaceInfoExample.*;

/**
 * 【表空间】详情展示对象
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "【表空间】详情展示对象")
public class TablespaceInfoShowVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID,example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_TABLESPACE_NAME,example = E_TABLESPACE_NAME)
    private String tablespaceName;

    @ApiModelProperty(notes = N_TABLESPACE_DESC,example = E_TABLESPACE_DESC)
    private String tablespaceDesc;

    @ApiModelProperty(notes = N_CODE_MODE,example = E_CODE_MODE, allowableValues = CodeMode.VALUES_STR)
    private Integer codeMode;

    @ApiModelProperty(notes = N_ENCRYPTION_MODE,example = E_ENCRYPTION_MODE, allowableValues = EncryptionMode.VALUES_STR)
    private Integer encryptionMode;

    @ApiModelProperty(notes = N_TO_CHAIN,example = E_TO_CHAIN, allowableValues = ChainType.VALUES_STR)
    private Integer toChain;

    @ApiModelProperty(notes = N_CREATE_TIME,example = E_CREATE_TIME)
    @JsonFormat(pattern=JsonFieldConst.DEFAULT_DATETIME_FORMAT,timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(notes = N_UPDATE_TIME,example = E_UPDATE_TIME)
    @JsonFormat(pattern=JsonFieldConst.DEFAULT_DATETIME_FORMAT,timezone="GMT+8")
    private Date updateTime;

    @ApiModelProperty(notes = N_CREATOR_ID,example = E_CREATOR_ID)
    private String creatorId;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTablespaceName() {
        return this.tablespaceName;
    }

    public void setTablespaceName(String tablespaceName) {
        this.tablespaceName = tablespaceName;
    }

    public String getTablespaceDesc() {
        return this.tablespaceDesc;
    }

    public void setTablespaceDesc(String tablespaceDesc) {
        this.tablespaceDesc = tablespaceDesc;
    }

    public Integer getCodeMode() {
        return this.codeMode;
    }

    public void setCodeMode(Integer codeMode) {
        this.codeMode = codeMode;
    }

    public Integer getEncryptionMode() {
        return this.encryptionMode;
    }

    public void setEncryptionMode(Integer encryptionMode) {
        this.encryptionMode = encryptionMode;
    }

    public Integer getToChain() {
        return this.toChain;
    }

    public void setToChain(Integer toChain) {
        this.toChain = toChain;
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

