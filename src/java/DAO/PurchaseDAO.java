/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import db.Conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Purchase;

/**
 *
 * @author Pablo
 */
public class PurchaseDAO {
    
    private static PurchaseDAO instance;
    public static PurchaseDAO getInstance() {
    if (instance == null) {
        instance = new PurchaseDAO();
    }
        return instance;
    }
    private PurchaseDAO() {}
    
    
    public ArrayList<Purchase> getPurchaseHistory(String username) throws ClassNotFoundException,
SQLException {
        
    // GET user ID
    UserDAO user = UserDAO.getInstance();
    String idUser = user.getId(username);
    
    String sql = "select id_purchase,purchaseDate from purchase where id_person='"+idUser+"'";
    ArrayList<Purchase> res = new ArrayList();
   
    
        try (
                Connection connection=Conection.getConnection();
                PreparedStatement prepStmt = connection.prepareStatement(sql);
        ){
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                String sql2 = "select product_id,quantity from orders where order_id='"+rs.getString(1)+"'";
                PreparedStatement prepStmt2 = connection.prepareStatement(sql2);
                ResultSet rs2 = prepStmt2.executeQuery();
                while(rs2.next()){
                    String getProductNamePrice = "select name,unitPrice from products where id='"+rs2.getString(1)+"'";
                    PreparedStatement prepStmt3 = connection.prepareStatement(getProductNamePrice);
                    ResultSet rsTmp = prepStmt3.executeQuery();
                    while(rsTmp.next()){
                        res.add(new Purchase(rsTmp.getString(1), rs.getString(2), rs2.getString(2), rsTmp.getString(2)));
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return res;
}
    
    
    
    public static void addPurchase(ArrayList<String> productName, String username, ArrayList<String> quantity, int num_items ) throws ClassNotFoundException,
SQLException {
    String url = "jdbc:mysql://localhost:3306/jdbcex";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, "jdbcuser",
   "password");
    Statement getIdProduct = con.createStatement();
    Statement getIdUser = con.createStatement();
    Statement instr = con.createStatement();
    
    
    // GET user ID
    String idUser="0";
    String idUserSQL = "select * from users where username='"+username+"'";
    ResultSet idusername = instr.executeQuery(idUserSQL);
    if(idusername.next()){
        idUser = idusername.getString("id");
    }
    
    
    System.out.println("busca " + idUser + " " + username);
    
    
    String sql = "insert into purchase (id_person) values ('"+idUser+"')";
    instr.executeUpdate(sql);
    
    for(int i=0; i<num_items; i++){

        /* ArrayProducts.get(0) has an amount of ArrayQuantity.get(0) */
        
        // GET PRODUCT ID
        String idProduct="";
        String idProductSQL = "select * from products where name='"+productName.get(i)+"'";
        ResultSet idproduct = instr.executeQuery(idProductSQL);
        if(idproduct.next()){
            idProduct = idproduct.getString("id");
        }
        
        // GET purchase ID --- 
        String idPurchase="";
        String idPurchaseSQL = "select LAST_INSERT_ID() from purchase where id_person='"+idUser+"'";
        ResultSet idPurchaseRS = instr.executeQuery(idPurchaseSQL);
        if(idPurchaseRS.next()){
            idPurchase = idPurchaseRS.getString(1); // 1 = first column 
        }       
        
        sql = "insert into orders (order_id,product_id,quantity) values ('"+idPurchase+"','"+idProduct+"','"+quantity.get(i)+"')";
        instr.executeUpdate(sql);
        
    }
    

   instr.close();
    con.close();
 }
    
    
    
}
    

