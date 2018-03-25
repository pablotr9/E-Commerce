/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.List;
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
                   out.println("<html>\n" +
"    <head>\n" +
"        <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\"/>\n" +
"        <title>BuyController</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width\">\n" +
"    </head>\n" +
"    <body>\n <h1> Cart </h1> <br><br>");
                   double sum = 0;
                   //List<Product> products=(List<Product>) getServletContext().getAttribute("PRODUCTS");

                   for(int i=0; i<max;i++){
                         int quantity = parseInt( (String) request.getParameter("productQtty"+i));
                         String name = request.getParameter("productName"+i);
                         double price = Double.valueOf(request.getParameter("productPrice"+i));
                         
                         if(quantity>0){                           
                            out.println("<br>Name " + name + "<br>Price " + price + "<br>Quantity " + quantity +"<br><br>");
                            sum += price * quantity;
                         }                         
                   }
                   out.println( "<br><br>Total price: " + sum +
"    </body>\n" +
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
