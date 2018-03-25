/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
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
public class BuyController extends HttpServlet {

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
       
        int max = parseInt( (String) request.getParameter("max"));
        
        
            try (PrintWriter out = response.getWriter()) {
                
                    /* TODO output your page here. You may use following sample code. */
               
                   double sum = 0;
                   int num_items=0;
                   ArrayList<String> nameProducts = new ArrayList();
                   ArrayList<String> qttyProducts = new ArrayList();
                   ArrayList<String> priceProducts = new ArrayList();
                   //List<Product> products=(List<Product>) getServletContext().getAttribute("PRODUCTS");

                   for(int i=0; i<max;i++){
                       //???
                         String quantity =  request.getParameter("productQtty"+i);
                         int quantityInt = parseInt ( (String) quantity);
                         String name = request.getParameter("productName"+i);
                         String priceString = request.getParameter("productPrice"+i);
                         double price = Double.valueOf(priceString);
                         
                         if(quantityInt>0){
                             nameProducts.add(name);
                             qttyProducts.add(quantity);
                             priceProducts.add(priceString);
                             
                             sum += price * quantityInt;
                             num_items++;
                         }                         
                   }
                   String username = "null";
                   if(request.getSession(false) != null &&
                    request.getSession().getAttribute("USER")!=null){
                        username=(String) request.getSession().getAttribute("USER");
                   }
                    out.println(username);
                       try {
                           Conection.addPurchase(nameProducts, username, qttyProducts, num_items);
                       } catch (ClassNotFoundException ex) {
                           Logger.getLogger(BuyController.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (SQLException ex) {
                           Logger.getLogger(BuyController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   request.setAttribute("namepro",nameProducts);
                   request.setAttribute("qttypro", qttyProducts);
                   request.setAttribute("pricepro", priceProducts);
                   request.setAttribute("sum", sum);
                   request.setAttribute("num_items", num_items);
                   request.getRequestDispatcher("TransactionView").forward(request, response);

                
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
