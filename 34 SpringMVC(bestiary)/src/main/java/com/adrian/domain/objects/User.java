package com.adrian.domain.objects;

import javafx.scene.chart.PieChart;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {
    private String userId;
    private String name;
    @Size(min = 1, max = 40, message = "{Size.user.logAndPass.validation}")
    private String login;
    @Size(min = 1, max = 40, message = "{Size.user.logAndPass.validation}")
    private String password;
    //private Date dateOfBirth;         zajac sie pozniej

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
