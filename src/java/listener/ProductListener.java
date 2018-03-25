/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.Product;

/**
 * Web application lifecycle listener.
 *
 * @author Pablo
 */



public class ProductListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ArrayList<Product> myProducts = new ArrayList();
        
            
        try {
            myProducts = getProducts();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        sce.getServletContext().setAttribute("PRODUCTS",myProducts);

        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    public ArrayList<Product> getProducts() throws ClassNotFoundException,
SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcex";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, "jdbcuser",
   "password");
    Statement instr = con.createStatement();
    
    ArrayList<Product> res = new ArrayList();
    
    String sql = "SELECT * FROM products";
    ResultSet rs = instr.executeQuery(sql);
    

    while(rs.next()){
        res.add(new Product(rs.getString("id"), rs.getString("name"),rs.getString("description"), rs.getString("type"), Double.valueOf(rs.getString("unitPrice"))));
      
    }
    
    

    rs.close();
   instr.close();
    con.close();
    return res;
}
    
    
    
}
