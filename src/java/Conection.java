import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import model.Purchase;
public class Conection {
public static boolean userExists(String arg) throws ClassNotFoundException,
SQLException {
    String url = "jdbc:mysql://localhost:3306/jdbcex";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, "jdbcuser",
   "password");
    Statement instr = con.createStatement();
    String sql = "SELECT username FROM users where username='"+arg+"'";
    ResultSet rs = instr.executeQuery(sql);
    boolean res = false;
    if(rs.next()){
        res = true;
    }

    rs.close();
   instr.close();
    con.close();
    return res;
 }




public static void addUser(String name, String username, String pass, String email, String gender, String tel, String country, String spam) throws ClassNotFoundException,
SQLException {
    String url = "jdbc:mysql://localhost:3306/jdbcex";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, "jdbcuser",
   "password");
    Statement instr = con.createStatement();
    if(gender.equals("male"))
        gender="m";
    else
        gender="f";
    
    pass = getHash(pass);
    
    //FIX SPAM,  *************************************
    String sql = "insert into users (name, username, password,tel,email,gender,country,spam) values ('"+name+"','"+username+"','"+pass+"',"+tel+",'"+email+"','"+gender+"','"+country+"',"+1+")";
    int rs = instr.executeUpdate(sql);
    

   instr.close();
    con.close();
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


public static ArrayList<Purchase> getPurchaseHistory(String username) throws ClassNotFoundException,
SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcex";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, "jdbcuser",
   "password");
    Statement instr = con.createStatement();
    Statement instr2 = con.createStatement();
    Statement instr3 = con.createStatement();
    Statement getIdUser = con.createStatement();
    
    
    // GET user ID
    String idUser="0";
    String idUserSQL = "select * from users where username='"+username+"'";
    ResultSet idusername = instr.executeQuery(idUserSQL);
    if(idusername.next()){
        idUser = idusername.getString("id");
    }
    
    ArrayList<Purchase> res = new ArrayList();
    
    
    
    String sql = "select id_purchase,purchaseDate from purchase where id_person='"+idUser+"'";
    ResultSet rs = instr.executeQuery(sql);
    
    while(rs.next()){
        sql = "select product_id,quantity from orders where order_id='"+rs.getString(1)+"'";
        ResultSet rs2 = instr2.executeQuery(sql);
        
        while(rs2.next()){
            String getProductNamePrice = "select name,unitPrice from products where id='"+rs2.getString(1)+"'";
            ResultSet rsTmp = instr3.executeQuery(getProductNamePrice);
            while(rsTmp.next()){
                res.add(new Purchase(rsTmp.getString(1), rs.getString(2), rs2.getString(2), rsTmp.getString(2)));
            }
        }
    }
    
    

    rs.close();
   instr.close();
    con.close();
    return res;
}








public static String getHash(String password) {
    MessageDigest digest=null;
    try {
    digest = MessageDigest.getInstance("SHA-1");
    } catch (NoSuchAlgorithmException ex) {
        ex.printStackTrace();
    }
    digest.reset();
    try {
    digest.update(password.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException ex) {
        ex.printStackTrace();
    }
    return new BigInteger(1, digest.digest()).toString(16);
}



public static ArrayList<String> getProducts() throws ClassNotFoundException,
SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcex";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, "jdbcuser",
   "password");
    Statement instr = con.createStatement();
    
    ArrayList<String> res = new ArrayList();
    
    String sql = "SELECT * FROM products";
    ResultSet rs = instr.executeQuery(sql);
    
    while(rs.next()){
        res.add(rs.getString("name"));
        res.add(rs.getString("unitPrice"));
        res.add(rs.getString("description"));
        res.add(rs.getString("type"));
    }
    
    

    rs.close();
   instr.close();
    con.close();
    return res;
}

public static boolean isPasswordCorrect(String username, String password) throws ClassNotFoundException,
SQLException {
    String url = "jdbc:mysql://localhost:3306/jdbcex";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, "jdbcuser",
   "password");
    Statement instr = con.createStatement();
    
    password = getHash(password);
    String sql = "SELECT username FROM users WHERE username='"+username+"' AND password='"+password+"'";
    ResultSet rs = instr.executeQuery(sql);
    boolean res = false;
    if(rs.next()){
        res = true;
    }
        
     rs.close();
   instr.close();
    con.close();
    return res;
}



}



