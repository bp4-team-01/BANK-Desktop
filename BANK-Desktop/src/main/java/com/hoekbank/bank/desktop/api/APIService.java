package com.hoekbank.bank.desktop.api;

public enum APIService {
    USER_CREATE("/user/create"),
    USER_REJECT("/user/reject"),
    USER_LOGIN("/user/login"),
    ACCOUNT_LIST("/account/get/all");

    private String url;

    APIService(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }

}
