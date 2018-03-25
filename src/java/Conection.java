import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
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


// cambiar password a no string ********************************************************************************
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

}



