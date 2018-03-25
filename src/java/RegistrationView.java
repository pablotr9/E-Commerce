/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pablo
 */
public class RegistrationView extends HttpServlet {

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
            out.println("<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
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
"                <li><a href=\"ProductView\">View products</a></li>\n" +
"                <li><a href=\"#\">Contact</a></li>\n" +
"            </ul>\n" +
"        </nav>\n" +
"            <h1>Registration form</h1>   \n" +
"            <form method=\"post\" action=\"RegistrationController \"> \n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"name\">Name</label>\n" +
"                    <input type=\"text\" name=\"name\" id=\"name\" size=\"40\">\n" +
"                </div>\n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"username\">Username</label>\n" +
"                    <input type=\"text\" name=\"uname\" id=\"uname\">\n" +
"                </div>\n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"password\">Password</label>\n" +
"                    <input type=\"password\" name=\"password\" id=\"password\">\n" +
"                </div>\n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"rPassword\">Repeat password</label>\n" +
"                    <input type=\"password\" name=\"rPassword\" id=\"rPassword\">\n" +
"                </div>\n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"email\">Email</label>\n" +
"                    <input type=\"email\" id=\"email\" name=\"email\">\n" +
"                </div>\n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"gender\">Gender</label>\n" +
"                    Male<input type=\"radio\" name=\"gender\" value=\"male\"> \n" +
"                    Female<input type=\"radio\" name=\"gender\" value=\"female\"> \n" +
"                </div>\n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"tel\">Telephone</label>\n" +
"                    <input type=\"tel\" name=\"tel\" id=\"tel\">\n" +
"                </div>\n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"country\">Country</label>\n" +
"                    <select name=\"country\" id=\"country\">\n" +
"                        <option>Romania</option>\n" +
"                        <option>Italy</option>\n" +
"                        <option>France</option>\n" +
"                        <option>Germany</option>\n" +
"                    </select>\n" +
"                </div>\n" +
"                <div class=\"form-element\">\n" +
"                    <label for=\"spam\">Subscribe to maillist</label>\n" +
"                    <input type=\"checkbox\" name=\"spam\" id=\"spam\">\n" +
"                </div> \n" +
"                  <div class=\"form-element\">\n" +
"                      <input type=\"submit\" value=\"Submit\">\n" +
"                    <input type=\"reset\" value=\"Reset\">\n" +
"                </div>  \n" +
"                      \n" +
"                    \n" +
"            </form>\n" +
"        </div>\n");
           List<String> errors=(List<String>) request.getAttribute("errors");
            if(errors!=null && !errors.isEmpty()){
                for(String error : errors)
                    out.println(error+"</br>");
            }
            out.println("  </body>\n" +
"</html>\n" +
"");
            
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
