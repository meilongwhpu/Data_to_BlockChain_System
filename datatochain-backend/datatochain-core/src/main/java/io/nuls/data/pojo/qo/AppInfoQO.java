package io.nuls.data.pojo.qo;

import io.nuls.common.pojo.qo.PageQO;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;

import static io.nuls.data.pojo.example.AppInfoExample.*;

/**
 * 查询【应用】的参数
 *
 * @author Long
 * @date 2020/03/16
 */
public class AppInfoQO extends PageQO {

    @ApiParam(value = N_APP_NAME,example = E_APP_NAME)
    @Length(max = 50,message = "appName最大长度不能超过{max}")
    private String appName;


    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}

