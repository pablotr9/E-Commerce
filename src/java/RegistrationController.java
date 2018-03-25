/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pablo
 */
public class RegistrationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    private void printErrorRegister(String arg, PrintWriter out){
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
                        "                <li><a href=\"#\">Home</a></li>\n" +
                        "                <li><a href=\"#\">Register</a></li>\n" +
                        "                <li><a href=\"#\">Contact</a></li>\n" +
                        "            </ul>\n" +
                        "        </nav>\n"  + "<h2> Wrong " + arg + ", please enter the registration form correctly" +
                                                                                "    </body>\n" +
                                                                                "</html>\n" +
                                                                                "");   
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("name");
        String username = request.getParameter("uname");
        String password = request.getParameter("password");
        String rPassword = request.getParameter("rPassword");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String telephone = request.getParameter("tel");
        String country = request.getParameter("country");
        String spam = request.getParameter("spam");
        
        
        
        
             try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                
                    ArrayList<String> errors = new ArrayList();
                  if(name.length() <= 3){
                        errors.add("Name has to be 3+ chars long");
                    }
                    if(username.length() < 3){
                        errors.add("Username has to be 3+ chars long");
                    }
                    if(password.length() < 6){                        
                        errors.add("Password has to be 6+ chars long");
                    }
                    if(!password.equals(rPassword)){
                        errors.add("The passwords you entered do not match");                         
                    }
                    if(email.isEmpty()){
                        errors.add("Email field can't be empty");
                    }
                    if(!"male".equals(gender) && !"female".equals(gender)){
                        errors.add("Gender field can't be empty");
                    }
                    if(telephone.length() < 9){
                        errors.add("Telephone has to be 9+ long");
                    }
                    if(Conection.userExists(request.getParameter("uname"))){
                        errors.add("That username already exists!!!");

                    }
                    
                    if( errors.isEmpty() ){
                        Conection.addUser(name,username,password,email,gender,telephone,country,spam);
                        //ENVIAR A LOGIN VIEW CUANDO SE REGISTRA CORRECTAMENTE
//request.getRequestDispatcher("LoginView").forward(request, response);
                        
                    }else{
                               request.setAttribute("errors", errors);
                               request.getRequestDispatcher("RegistrationView").forward(request, response);
                    }
       
       
// 
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
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
