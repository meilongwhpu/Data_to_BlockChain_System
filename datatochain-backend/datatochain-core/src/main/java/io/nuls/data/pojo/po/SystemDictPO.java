package io.nuls.data.pojo.po;

import io.nuls.common.pojo.po.AbstractPO;

/**
 * 系统参数
 * <p>系统参数字典表
 *
 * @author Long
 * @date 2020/03/16
 */
public class SystemDictPO extends AbstractPO {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 参数key值
     */
    private String dictKey;

    /**
     * 参数值
     */
    private String dictValue;

    /**
     * 参数描述
     */
    private String dictDesc;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictKey() {
        return this.dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return this.dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictDesc() {
        return this.dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }


}

