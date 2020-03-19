package io.nuls.common.context;


/**
 * 登录上下文接口
 *
 * @author Long
 * @date 2020/03/16
 */
public interface LoginContext {

    /**
     * 获取当前登录用户唯一标识
     *
     * @return 用户唯一标识
     */
    String getCurrentUser();

}

