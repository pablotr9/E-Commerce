<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
    
            
 <html>
  
    <%@include file="/WEB-INF/jspf/header.jspf" %>
         
  <body> <h1> shopping cart Jsp </h1> <br><br>
                    
<form method="get" action="DeleteItemShoppingCart">
    <c:forEach items="${sessionScope.SHOPPINGCART}" var="products"
        varStatus="pro"
        begin="0" step="1">
        Name: ${products.productName} <br> Price: ${products.price} <br>  Qtty: ${products.quantity} <br><br>
        
        <button type="submit" name="${pro.index}" >Delete</button> <br>
        
    </c:forEach>
</form>
      
      
  <c:if test="${sessionScope.SHOPPINGCART==null}" var="SHOPPINGCART" >
      <h2> ${requestScope.txt} </h2>
  </c:if>
       
      
 <c:if test="${sessionScope.SHOPPINGCART!=null}" var="SHOPPINGCART" >
      <br><br>Total price: ${sessionScope.sum}
        
      <form method="get" action="emptyShoppingCart">
          <button type="submit">Empty ShoppingCart</button>
      </form>
      <form method="get" action="ConfirmPurchase">
          <button type="submit">Confirm purchase</button>
      </form>
  </c:if>
    
      
<br>   </body>
  
              <%@include file="/WEB-INF/jspf/footer.jspf" %>  
</html>