<%-- 
    Document   : PreviousPurchasesJSP
    Created on : 26-mar-2018, 11:27:32
    Author     : Pablo
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Purchase"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 
<!DOCTYPE html>
                <html>
                  
    <%@include file="/WEB-INF/jspf/header.jspf" %>
         
    
            <c:if test ="${sessionScope.USER!=null}" var="USER" >
                <c:forEach items="${requestScope.history}" var="history"
                    varStatus="pro"
                    begin="0" step="1">
                    
                        <br><br> <h1> Purchase with date: ${history.date}</h1><br>
                        Name: ${history.productName} Price: ${history.price} Qtty: ${history.quantity}
                    
                    
                </c:forEach>
            </c:if>
    
    
           
                
                 
                 </body>
                 
              <%@include file="/WEB-INF/jspf/footer.jspf" %>  
                </html>
