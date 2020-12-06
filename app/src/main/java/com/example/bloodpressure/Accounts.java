package com.example.bloodpressure;

public class Accounts {
    private String username, password;
    private int accountId;
    public Accounts(){}
    public Accounts(int accountId, String username, String password) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
    }
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

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
