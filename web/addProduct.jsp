<%-- 
    Document   : addProduct.jsp
    Created on : 30-abr-2018, 13:50:22
    Author     : Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    
    <%@include file="/WEB-INF/jspf/header.jspf" %>
    
     <form method="post" action="addProduct">  
              <div class="form-element"> 
                  <label for="name">Product name </label> 
                  <input type="text" name="name" id="name" size="40"> 
              </div> 
              <div class="form-element"> 
                  <label for="type">Type</label> 
                  <input type="text" name="type" id="type"> 
              </div> 
              <div class="form-element"> 
                  <label for="description">Description</label> 
                  <input type="text" name="description" id="description"> 
              </div>
              <div class="form-element"> 
                  <label for="price">Price</label> 
                  <input type="text" id="price" name="price"> 
              </div>
                  <div class="form-element"> 
                      <input type="submit" value="Submit"> 
                    <input type="reset" value="Reset"> 
                </div>   
                       
                     
            </form> 
        
        <c:if test ="${not empty requestScope.errors}" var="errors" >
                    
                <c:forEach items="${requestScope.errors}" var="errors"
                varStatus="err"
                begin="0" step="1">
                ${errors}</br>
                </c:forEach>       
                        
        </c:if>
    </body>
</html>
