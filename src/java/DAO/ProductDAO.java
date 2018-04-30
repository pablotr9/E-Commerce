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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Pablo
 */
public class ProductDAO {
    private static ProductDAO instance;
    public static ProductDAO getInstance() {
    if (instance == null) {
        instance = new ProductDAO();
    }
        return instance;
    }
    private ProductDAO() {}
    
    
    
    
    
    
    public ArrayList<Product> getProducts() {
        String sql="SELECT * FROM PRODUCTS";
        ArrayList<Product> res = new ArrayList<>();
        try (
            Connection connection= Conection.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            ){
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                res.add( new Product(rs.getString("id"), rs.getString("name"),rs.getString("description"), rs.getString("type"), Double.parseDouble(rs.getString("unitPrice")) ) );
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
        return res;
    }
    
    
    
    public static void addProduct(String productname, String price, String description, String type){
        try {
            String sql = "insert into products (name,type,description,unitPrice) values ('"+productname+"','"+type+"','"+description+"',"+Double.parseDouble(price)+")";
            Connection connection = Conection.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 }
    
    
    
}
