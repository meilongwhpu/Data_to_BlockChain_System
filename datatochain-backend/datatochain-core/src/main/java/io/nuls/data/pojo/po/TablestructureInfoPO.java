package io.nuls.data.pojo.po;

import io.nuls.common.pojo.po.AbstractPO;

import java.util.Date;

/**
 * 表结构
 *
 * @author Long
 * @date 2020/03/16
 */
public class TablestructureInfoPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 表空间ID
     */
    private String tablespaceId;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableDesc;

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

    public String getTablespaceId() {
        return this.tablespaceId;
    }

    public void setTablespaceId(String tablespaceId) {
        this.tablespaceId = tablespaceId;
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


}

