package com.adrian.domain.objects;

public class Comment {
    private int id;
    private String userLogin;
    private int beastId;
    private String content;
    //private date dateOfAdding;

    public Comment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getBeastId() {
        return beastId;
    }

    public void setBeastId(int beastId) {
        this.beastId = beastId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
