package io.nuls.data.pojo.po;

import java.util.List;
import java.util.Map;

/**
 * 登录用户信息
 *
 * @author Long
 * @date 2020/03/16
 */
public class TableDataValuePO {

    private Object value;
    private String type;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

