/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Pablo
 */
public class ProductView extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             boolean logged=false;
                if(request.getSession(false) != null && request.getSession().getAttribute("USER")!=null){
                    logged=true;
                }
                
            
           out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
"        <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\"/>\n" +
"        <title>Product View</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <div id=\"content\">\n" +
"        <div class=\"header\">\n" +
"            <img src=\"./img/companyLogo.jpg\" width=\"100px\"  />\n" +
"            <span class=\"header-text\">COMPANY NAME</span>\n" +
"        </div>\n" +
"        <nav>\n" +
"            <ul>\n" +
"                <li><a href=\"index.html\">Home</a></li>\n");
           if(logged){
                    out.println("<a href=\"LogoutController\">Logout</a>");
           }
           else{
               out.println("<li><a href=\"login.html\">Login</a></li>\n");
           }
           out.println(
"                <li><a href=\"#\">Contact</a></li>\n" +
"            </ul>\n" +
"        </nav>" );
                
                
                
               
                
                if(logged){
                    
                    List<Product> products=(List<Product>) getServletContext().getAttribute("PRODUCTS");
                        out.println("  <form method=\"post\" action=\"BuyController\">  "
                        + products.size() + " <input name=\"max\" type=\"hidden\" value=\"" + products.size() + "\"> \n " );
                
              
                    for(int i=0; i<products.size(); i++){
                       out.println("Name: " +  products.get(i).getName()  + "<br> Price " + String.valueOf(products.get(i).getPrice()) + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Quantity:" + "<input type=\"text\" name=\"productQtty"+i+"\" placeholder=\"0\" value=\"0\" size=\"1\">  <input  name=\"productName"+i+"\" value=\""+ products.get(i).getName() +"\" type=\"hidden\">  <input name=\"productPrice"+i+"\" value=\""+ products.get(i).getPrice() +"\" type=\"hidden\">  \n" + "<br> Description: " + products.get(i).getDescription() + " <br> Type: " + String.valueOf(products.get(i).getPrice()) +  " <br> <br> ");
                    }
                 
                    out.println("       <input type=\"submit\" value=\"Submit\">     </form>" );
                    
                    
                }else{
                    
                    
                    List<Product> products=(List<Product>) getServletContext().getAttribute("PRODUCTS");
                    
                    for(int i=0; i<products.size(); i++){
                       out.println("Name: " +  products.get(i).getName()  + "<br> Price " + String.valueOf(products.get(i).getPrice()) +  "<br> Description: " + products.get(i).getDescription() + " <br> Type: " + String.valueOf(products.get(i).getPrice()) +  " <br> <br> ");
                    }
                 
                    
                }
           
                    
                 out.println(
                " </body>\n" +
                "</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
