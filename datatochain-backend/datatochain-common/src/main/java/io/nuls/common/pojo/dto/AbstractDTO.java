package io.nuls.common.pojo.dto;

import io.nuls.common.util.JsonUtil;

import java.io.Serializable;

/**
 * 数据传输对象超类
 *
 * @author Long
 * @date 2020/03/16
 */
public abstract class AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1915714417292764241L;

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }

}

