/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pablo
 */
public class TransactionView extends HttpServlet {

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
                   out.println("<html>\n" +
"     <head>\n" +
"        <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\"/>\n" +
"        <title>Registration</title>\n" +
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
"                <li><a href=\"index.html\">Home</a></li>\n" +
"                <li><a href=\"login.html\">Login</a></li>\n" +
"                <li><a href=\"#\">Contact</a></li>\n" +
"            </ul>\n" +
"        </nav>" +
"    <body>\n <h1> Cart </h1> <br><br>");
                    
                        
                        for(int i=0; i< (int) request.getAttribute("num_items") ; i++){
                            out.println("<br>Name " + ((ArrayList<String>) request.getAttribute("namepro")).get(i) + "<br>Price " + ((ArrayList<String>) request.getAttribute("pricepro")).get(i) + "<br>Quantity " + ((ArrayList<String>) request.getAttribute("qttypro")).get(i) +"<br><br>");
                            
                        }
                   out.println( "<br><br>Total price: " + request.getAttribute("sum") +
"  <br>   </body>\n" +
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
