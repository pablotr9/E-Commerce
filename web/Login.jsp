<%-- 
    Document   : Login
    Created on : 26-mar-2018, 10:38:09
    Author     : Pablo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <%@include file="/WEB-INF/jspf/header.jspf" %>

            <h1>Registration form</h1>    
            <form method=post action=LoginController >                 
                <div class=form-element> 
                    <label for=username>Username</label> 
                    <input type=text name=uname id=uname> 
                </div> 
                <div class=form-element> 
                    <label for=password>Password</label> 
                    <input type=password name=password id=password> 
                </div>               
                  <div class=form-element> 
                      <input type=submit value=Submit> 
                    <input type=reset value=Reset> 
                </div>                    
                     
            </form>
            
            <c:if test ="${not empty requestScope.errors}" var="errors" >
                    
                <c:forEach items="${requestScope.errors}" var="errors"
                varStatus="err"
                begin="0" step="1">
                ${errors}</br>
                </c:forEach>       
                        
            </c:if>
            
        </div> 
    </body> 
    
              <%@include file="/WEB-INF/jspf/footer.jspf" %>  
</html> 
