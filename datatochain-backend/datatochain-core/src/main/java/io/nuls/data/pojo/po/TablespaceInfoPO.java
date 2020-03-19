package io.nuls.data.pojo.po;

import io.nuls.common.pojo.po.AbstractPO;

import java.util.Date;

/**
 * 表空间
 * <p>表空间信息表
 *
 * @author Long
 * @date 2020/03/16
 */
public class TablespaceInfoPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 表空间名称
     */
    private String tablespaceName;

    /**
     * 表空间描述
     */
    private String tablespaceDesc;

    /**
     * 编码格式
     * @see io.nuls.data.constant.CodeMode
     */
    private Integer codeMode;

    /**
     * 加密模式
     * @see io.nuls.data.constant.EncryptionMode
     */
    private Integer encryptionMode;

    /**
     * 上链位置
     * @see io.nuls.data.constant.ChainType
     */
    private Integer toChain;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建者ID
     */
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

