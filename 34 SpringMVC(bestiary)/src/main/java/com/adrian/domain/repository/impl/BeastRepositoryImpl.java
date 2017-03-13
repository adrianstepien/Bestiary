package com.adrian.domain.repository.impl;

import com.adrian.domain.objects.Beast;
import com.adrian.domain.repository.BeastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.activation.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BeastRepositoryImpl implements BeastRepository {
    private List<Beast> beasts = new ArrayList<Beast>();

    @Autowired
    private DriverManagerDataSource myDb;

    public BeastRepositoryImpl(){

    }

    @Override
    public List<Beast> getBeastsFromDb(){
        beasts.clear();
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM beast");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Beast beast = new Beast();
                beast.setBeastId(resultSet.getInt("ID"));
                beast.setHp(resultSet.getInt("hp"));
                beast.setStrength(resultSet.getInt("strength"));
                beast.setName(resultSet.getString("name"));
                beast.setCategory(resultSet.getString("category"));
                beast.setDescription(resultSet.getString("description"));
                beasts.add(beast);
            }
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return beasts;
    }

    @Override
    public List<Beast> getAllBeasts(){
        return beasts;
    }

    @Override
    public Beast getById(int beastId){
        for (Beast beast: beasts) {
            if(beast.getBeastId() == beastId){
                return beast;
            }
        }
        return null;
    }

    @Override
    public Beast getByName(String name){
        //trzeba napisac funkcje
        return null;
    }

    @Override
    public void leaveComment(String comment){

    }

    @Override
    public List<Beast> getGroupByCategory(String category){
        //napisz
        return null;
    }

    @Override
    public void addBeast(Beast beast){
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO beast.beast (name, strength, hp, category, description) VALUES (?,?,?,?,?)");
            ps.setString(1,beast.getName());
            ps.setInt(2,beast.getStrength());
            ps.setInt(3,beast.getHp());
            ps.setString(4,beast.getCategory());
            ps.setString(5,beast.getDescription());
            ps.executeUpdate();

                    //nadaje id nowostworzonej besti, aby obrazek mial taka sama nazwe jak id z bazy
            ps = conn.prepareStatement("SELECT * FROM beast.beast WHERE name=?");
            ps.setString(1, beast.getName());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                beast.setBeastId(rs.getInt("ID"));
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

            //modyfikuje dana bestie wedlug formularza wypelnionego przez admina
    public void update(Beast beast){
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE beast.beast SET name=?, strength=?, hp=?, category=?, description=? WHERE ID=?");
            ps.setString(1, beast.getName());
            ps.setInt(2, beast.getStrength());
            ps.setInt(3, beast.getHp());
            ps.setString(4,beast.getCategory());
            ps.setString(5,beast.getDescription());
            ps.setInt(6,beast.getBeastId());
            ps.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

            //usuwa bestie przez klikniecie buttona przez administratora
    public void delete(int beastId){
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM beast.beast WHERE id=?");
            ps.setInt(1, beastId);
            ps.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
