/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Purchase;

/**
 *
 * @author Pablo
 */
public class DeleteItemShoppingCart extends HttpServlet {

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
        ArrayList<Purchase> shoppingcart = (ArrayList<Purchase>) request.getSession().getAttribute("SHOPPINGCART");
        ArrayList<Purchase> shoppingcart2 = new ArrayList();
        
       // System.out.println(request.getParameter("0"));
       // System.out.println(shoppingcart.size());

        int del=-1;
        if(shoppingcart != null){
            for(int i=0; i<shoppingcart.size();i++){
                if(request.getParameter(String.valueOf(i)) == null){
                    shoppingcart2.add(shoppingcart.get(i));
                    del = i;
                }
                    
            }
        }
       /* if(del != -1)
            shoppingcart.remove(del);
        */
       
       if(shoppingcart2.size() == 0){
           request.getSession().setAttribute("SHOPPINGCART",null);
           request.getSession().setAttribute("sum", null );
       }else{
           request.getSession().setAttribute("SHOPPINGCART",shoppingcart2);
           double sum = (double) request.getSession().getAttribute("sum");
           sum -= shoppingcart.get(del).getTotal();
           request.getSession().setAttribute("sum", sum );
       }
            
      
        
        request.getRequestDispatcher("cart.jsp").forward(request, response);

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
