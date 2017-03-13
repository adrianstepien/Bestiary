package com.adrian.domain.repository.impl;

import com.adrian.domain.objects.Comment;
import com.adrian.domain.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository{
    @Autowired
    private DriverManagerDataSource myDb;

    private List<Comment> listOfComments = new ArrayList<Comment>();

    @Override
    public Comment create(Comment comment){
        //zakladam ze login uzytkownika i idBestii sa wprowadzone dobrze
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT into beast.comments (Beast_Id, User_Login, content) VALUES (?,?,?)");
            ps.setInt(1, comment.getBeastId());
            ps.setString(2, comment.getUserLogin());
            ps.setString(3, comment.getContent());
            ps.execute();
                    //pobieram Id dodanego wlasnie komentarza
            ps = conn.prepareStatement("SELECT ID FROM beast.comments ORDER BY ID DESC LIMIT 1;");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                comment.setId(rs.getInt("ID"));
            }
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return comment;
    }

    @Override
    public Comment readSingleCommentById(int commentId){
        Comment comment = new Comment();
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM beast.comments WHERE ID=?");
            ps.setInt(1, commentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                comment.setId(rs.getInt("ID"));
                comment.setContent(rs.getString("content"));
                comment.setUserLogin(rs.getString("User_Login"));
                comment.setBeastId(rs.getInt("Beast_Id"));
            }
            conn.close();
            return comment;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        //w razie bledow w sql
        return null;
    }

    @Override
    public List readById(int beastId){
        listOfComments.clear();
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM beast.comments WHERE Beast_Id=?");
            ps.setInt(1, beastId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Comment comment = new Comment();
                comment.setId(rs.getInt("ID"));
                comment.setBeastId(rs.getInt("Beast_Id"));
                comment.setUserLogin(rs.getString("User_Login"));
                comment.setContent(rs.getString("content"));
                listOfComments.add(comment);
            }
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return listOfComments;
    }

    @Override
    public Comment update(Comment comment){
        try{
        //szukam po ID danego komantarza a nastepnie go eytuje
        //zakladam ze obiekt ma juz dobrze przpisane id
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE beast.comments SET content=? WHERE ID=? AND User_Login=? AND Beast_Id=?");
            ps.setString(1, comment.getContent());
            ps.setInt(2, comment.getId());
            ps.setString(3, comment.getUserLogin());
            ps.setInt(4, comment.getBeastId());
            ps.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return comment;
    }

    @Override
    public void delete(int commentId){
        try{
            Connection conn = myDb.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM beast.comments WHERE ID=?");
            ps.setInt(1, commentId);
            ps.executeUpdate();
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
