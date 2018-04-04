/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import db.Conection;
import static db.Conection.getHash;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author Pablo
 */
public class UserDAO {
    private static UserDAO instance;
    public static UserDAO getInstance() {
    if (instance == null) {
        instance = new UserDAO();
    }
        return instance;
    }
    private UserDAO() {}
    
     public String getId(String username) {
        String res = null;
        String sql="select * from users where username='"+username+"'";
        
        try (
            Connection connection= Conection.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            ){
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                res=rs.getString("id");
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
        return res;
    }
     
     
    public boolean isPasswordCorrect(String username,String password) {
        boolean res = false;
        password = getHash(password);
        String sql = "SELECT username FROM users WHERE username='"+username+"' AND password='"+password+"'";
        
        try (
             Connection connection= Conection.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(sql);
             ){
             ResultSet rs = prepStmt.executeQuery();
             if(rs.next()){
                 res=true;
             }
         } catch (SQLException ex) {
         ex.printStackTrace();
         }
       
        return res;
    }
    
}
