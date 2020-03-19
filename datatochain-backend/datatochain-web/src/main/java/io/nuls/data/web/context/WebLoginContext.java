package io.nuls.data.web.context;

import io.nuls.common.context.LoginContext;
import org.springframework.stereotype.Component;

/**
 * web登录用户上下文
 *
 * @author Long
 * @date 2020/03/16
 */
@Component
public class WebLoginContext implements LoginContext{

    /**
     * 获取当前操作员id
     * @return
     */
    @Override
    public String getCurrentUser() {
        return "admin";
    }

}

