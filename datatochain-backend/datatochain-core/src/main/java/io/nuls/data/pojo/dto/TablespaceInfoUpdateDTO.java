package io.nuls.data.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.nuls.common.constant.JsonFieldConst;
import io.nuls.common.pojo.dto.AbstractDTO;
import io.nuls.common.validator.Const;
import io.nuls.data.constant.ChainType;
import io.nuls.data.constant.CodeMode;
import io.nuls.data.constant.EncryptionMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static io.nuls.data.pojo.example.TablespaceInfoExample.*;

/**
 * 修改【表空间】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel(description = "修改【表空间】的参数")
public class TablespaceInfoUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID,example = E_ID,required = true)
    @NotNull
    private Integer id;

    @ApiModelProperty(notes = N_TABLESPACE_NAME,example = E_TABLESPACE_NAME,required = true)
    @NotNull
    @Length(max = 50)
    private String tablespaceName;

    @ApiModelProperty(notes = N_TABLESPACE_DESC,example = E_TABLESPACE_DESC)
    @Length(max = 50)
    private String tablespaceDesc;

    @ApiModelProperty(notes = N_CODE_MODE,example = E_CODE_MODE,required = true, allowableValues = CodeMode.VALUES_STR)
    @NotNull
    @Const(constClass = CodeMode.class)
    private Integer codeMode;

    @ApiModelProperty(notes = N_ENCRYPTION_MODE,example = E_ENCRYPTION_MODE,required = true, allowableValues = EncryptionMode.VALUES_STR)
    @NotNull
    @Const(constClass = EncryptionMode.class)
    private Integer encryptionMode;

    @ApiModelProperty(notes = N_TO_CHAIN,example = E_TO_CHAIN,required = true, allowableValues = ChainType.VALUES_STR)
    @NotNull
    @Const(constClass = ChainType.class)
    private Integer toChain;

    @ApiModelProperty(notes = N_CREATE_TIME,example = E_CREATE_TIME,required = true)
    @NotNull
    @JsonFormat(pattern=JsonFieldConst.DEFAULT_DATE_FORMAT,timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(notes = N_UPDATE_TIME,example = E_UPDATE_TIME)
    @JsonFormat(pattern=JsonFieldConst.DEFAULT_DATE_FORMAT,timezone="GMT+8")
    private Date updateTime;

    @ApiModelProperty(notes = N_CREATOR_ID,example = E_CREATOR_ID)
    @Length(max = 50)
    private String creatorId;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

