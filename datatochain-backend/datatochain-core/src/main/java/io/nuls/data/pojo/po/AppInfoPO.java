package io.nuls.data.pojo.po;

import io.nuls.common.pojo.po.AbstractPO;

import java.util.Date;
import java.util.List;

/**
 * 应用
 *
 * @author Long
 * @date 2020/03/16
 */
public class AppInfoPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用介绍
     */
    private String appDesc;

    /**
     * 应用key，访问应用下面的数据时使用
     */
    private String appKey;

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

    private List<TablespaceInfoPO> tablespaceInfoPOList;

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

    public List<TablespaceInfoPO> getTablespaceInfoPOList() {
        return this.tablespaceInfoPOList;
    }

    public void setTablespaceInfoPOList(List<TablespaceInfoPO> tablespaceInfoPOList) {
        this.tablespaceInfoPOList = tablespaceInfoPOList;
    }


}

