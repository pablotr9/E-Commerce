/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Pablo
 */
public class Purchase {
    public String idPurchase; //??
    public String productName;
    public String date;
    public String quantity;
    public String price;
    
    
    public Purchase(String productName, String date, String quantity, String price) {
        this.productName = productName;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }
    
    public double getTotal(){
      return Double.parseDouble(quantity) * Double.parseDouble(price);  
    }
    
}
