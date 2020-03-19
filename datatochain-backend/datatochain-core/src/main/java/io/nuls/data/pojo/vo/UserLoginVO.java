package io.nuls.data.pojo.vo;

import java.util.List;

/**
 * 登录用户信息
 *
 * @author Long
 * @date 2020/03/16
 */
public class UserLoginVO {

    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

