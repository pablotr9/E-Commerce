/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import db.Conection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    
    
}
