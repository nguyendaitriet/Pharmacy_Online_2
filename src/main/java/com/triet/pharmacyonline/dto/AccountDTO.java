package com.triet.pharmacyonline.dto;

import java.util.Arrays;

public class AccountDTO {
    private int totalAccount;
    private int activeUser;
    private int blockedUser;
    private int activeAdmin;
    private int blockedAdmin;

    public AccountDTO() {
    }

    public AccountDTO(int totalAccount, int activeUser, int blockedUser, int activeAdmin, int blockedAdmin) {
        this.totalAccount = totalAccount;
        this.activeUser = activeUser;
        this.blockedUser = blockedUser;
        this.activeAdmin = activeAdmin;
        this.blockedAdmin = blockedAdmin;
    }

    public int getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(int totalAccount) {
        this.totalAccount = totalAccount;
    }

    public int getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(int activeUser) {
        this.activeUser = activeUser;
    }

    public int getBlockedUser() {
        return blockedUser;
    }

    public void setBlockedUser(int blockedUser) {
        this.blockedUser = blockedUser;
    }

    public int getActiveAdmin() {
        return activeAdmin;
    }

    public void setActiveAdmin(int activeAdmin) {
        this.activeAdmin = activeAdmin;
    }

    public int getBlockedAdmin() {
        return blockedAdmin;
    }

    public void setBlockedAdmin(int blockedAdmin) {
        this.blockedAdmin = blockedAdmin;
    }

}
