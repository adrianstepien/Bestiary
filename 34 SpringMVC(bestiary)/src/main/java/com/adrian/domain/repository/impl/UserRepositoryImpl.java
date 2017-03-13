package com.adrian.domain.repository.impl;

import com.adrian.domain.objects.User;
import com.adrian.domain.repository.UserRepository;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private DriverManagerDataSource myDb;

    private UserRepositoryImpl(){}

    public User create(User user){
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM beast.user WHERE login=?");
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();
                    //jesli jest uzytkownik o takim loginie zwracane jest null
            while(rs.next()){
                System.out.println("nie da sie dodac");
                return null;
            }
            ps = conn.prepareStatement("INSERT INTO beast.user (login, password, enabled) VALUES (?,?,?)");
            ps.setString(1,user.getLogin());
            if(user.getLogin()==null)
                throw new SQLException();
            ps.setString(2,user.getPassword());
            if(user.getPassword()==null)
                throw new SQLException();
            ps.setInt(3, 1);
            ps.executeUpdate();

            ps = conn.prepareStatement("INSERT into beast.user_role (username, role) VALUES (?,?)");
            ps.setString(1, user.getLogin());
            ps.setString(2, "ROLE_user");
            ps.executeUpdate();

            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }

    public User read(String userLogin){
        User user = new User();
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM beast.user WHERE login=?");
            ps.setString(1, userLogin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setUserId(Integer.toString(rs.getInt("ID")));
            }
            conn.close();
            return user;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<User> readAll(){
        List<User> listOfUsers = new ArrayList<User>();
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM beast.user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setUserId(Integer.toString(rs.getInt("ID")));
                listOfUsers.add(user);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return listOfUsers;
    }

    public void update(String userLogin, User user){
        User us = read(userLogin);
        if (us.getLogin().equals(null)) {
            System.out.print("the user is empty");
        }else{
            try{
                Connection conn = myDb.getConnection();
                PreparedStatement ps = conn.prepareStatement("UPDATE beast.user set password=? WHERE login=?");
                ps.setString(1, user.getPassword());
                 if(user.getPassword()==null)
                      throw new SQLException();
                ps.setString(2,userLogin);
                ps.executeUpdate();
                conn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public void delete(String userLogin){
        User user = read(userLogin);
        if (user.getLogin().equals(null)){

        }else{
            try {
                        //wyrzucam wszystkie komentarze usera
                Connection conn = myDb.getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM comments WHERE User_Login=?");
                ps.setString(1,userLogin);
                ps.execute();
                        //wyrzucam wszytskie role danego usera
                ps = conn.prepareStatement("DELETE FROM user_role WHERE username=?");
                ps.setString(1,userLogin);
                ps.execute();
                        //wyrzucam usera
                ps = conn.prepareStatement("DELETE FROM user WHERE login=?");
                ps.setString(1,userLogin);
                ps.execute();
                conn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
