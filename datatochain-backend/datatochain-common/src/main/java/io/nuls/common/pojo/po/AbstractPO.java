package io.nuls.common.pojo.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽象PO
 *
 * @author Long
 * @date 2020/03/16
 */
public abstract class AbstractPO implements Serializable {

    private static final long serialVersionUID = 640619331196056814L;

    public void preInsert(String createdBy) {
        Date now = new Date();
        if (this instanceof Deleted) {
            ((Deleted) this).setDeleted(false);
        }
        if (this instanceof CreatedTime) {
            ((CreatedTime) this).setCreatedTime(now);
        }
        if (this instanceof OperatedTime) {
            ((OperatedTime) this).setOperatedTime(now);
        }
        if (this instanceof CreatedBy) {
            ((CreatedBy) this).setCreatedBy(createdBy);
        }
        if (this instanceof OperatedBy) {
            ((OperatedBy) this).setOperatedBy(createdBy);
        }
        if (this instanceof Version) {
            ((Version) this).setVersion(1);
        }
    }

    public void preUpdate(String operatedBy) {
        if (this instanceof OperatedTime){
            ((OperatedTime) this).setOperatedTime(new Date());
        }
        if (this instanceof OperatedBy) {
            ((OperatedBy) this).setOperatedBy(operatedBy);
        }
    }

    public void postUpdate() {
        if (this instanceof Version) {
            Version version = (Version) this;
            version.setVersion(version.getVersion() + 1);
        }
    }

}

