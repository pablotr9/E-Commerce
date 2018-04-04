<%-- 
    Document   : ProductView
    Created on : 26-mar-2018, 10:49:38
    Author     : Pablo
--%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
                <html>
                
    <%@include file="/WEB-INF/jspf/header.jspf" %>  
                
    
                <form method="post" action="BuyController">
                    <input name="max" type="hidden" value="${fn:length(requestScope.allProducts)}"> 
                <c:if test ="${sessionScope.USER!=null}" var="USER" >
                    <c:forEach items="${requestScope.allProducts}" var="products"
                    varStatus="pro"
                    begin="0" step="1">
                        
                    Name: ${products.name} <br> Price ${products.price} Quantity: <input type="text" name="productQtty${pro.index}" placeholder="0" value="0" size="1">  <input  name="productName${pro.index}" value="${products.name}" type="hidden">  <input name="productPrice${pro.index}" value="${products.price}" type="hidden">  <br> Description: ${products.description} <br> Type: ${products.type} <br> <br> 
                  
                    </c:forEach>  
                    <input type="submit" value="Submit"> </form>
                </c:if>
                
                                       
                
                <c:if test ="${sessionScope.USER==null}" var="USER" >
                    <c:forEach items="${requestScope.allProducts}" var="products"
                    varStatus="pro"
                    begin="0" step="1">
                    Name: ${products.name} <br> Price ${products.price}   <br> Description: ${products.description} <br> Type: ${products.type} <br> <br> 
                  
                    </c:forEach>    
               </c:if>
                    
                    
                    
                    
           
                    
               
              </body>
              
              <%@include file="/WEB-INF/jspf/footer.jspf" %>  
              
              </html>