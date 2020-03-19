package io.nuls.common.pojo.qo;

import io.nuls.common.util.JsonUtil;

import java.io.Serializable;

/**
 * 数据查询参数对象超类
 *
 * @author Long
 * @date 2020/03/16
 */
public abstract class AbstractQO implements Serializable {

    private static final long serialVersionUID = -2460649808778841614L;

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }

}

