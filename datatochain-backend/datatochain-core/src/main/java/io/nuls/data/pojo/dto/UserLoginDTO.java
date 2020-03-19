package io.nuls.data.pojo.dto;


/**
 * 用户登录请求体
 *
 * @author Long
 * @date 2020/03/16
 */
public class UserLoginDTO {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

