package io.nuls.common.pojo.vo;

import io.nuls.common.util.JsonUtil;

import java.io.Serializable;

/**
 * 抽象VO
 *
 * @author Long
 * @date 2020/03/16
 */
public abstract class AbstractVO implements Serializable {

    private static final long serialVersionUID = -1417748095004687576L;

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }

}

