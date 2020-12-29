package com.musicalinstrument.demo.jpa;

public class User {
    private Long userId;
    private int fkRoleId;
    private String login;
    private String password;

    public int getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(int fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
