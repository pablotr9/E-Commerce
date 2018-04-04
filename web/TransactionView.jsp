<%-- 
    Document   : TransactionView
    Created on : 26-mar-2018, 11:13:10
    Author     : Pablo
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
    
            
 <html>
  
    <%@include file="/WEB-INF/jspf/header.jspf" %>
         
  <body> <h1> Purchase Jsp </h1> <br><br>
                    
      
    <c:forEach items="${requestScope.products}" var="products"
        varStatus="pro"
        begin="0" step="1">
        Name: ${products.productName} <br> Price: ${products.price} <br>  Qtty: ${products.quantity} <br><br>
    </c:forEach>
        
    <br><br>Total price: ${requestScope.sum} 
        
      
      
<br>   </body>
  
              <%@include file="/WEB-INF/jspf/footer.jspf" %>  
</html>