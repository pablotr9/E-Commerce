<%-- 
    Document   : Register
    Created on : 26-mar-2018, 10:41:10
    Author     : Pablo
--%>

<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
"<!-- 
"To change this license header, choose License Headers in Project Properties. 
"To change this template file, choose Tools | Templates 
"and open the template in the editor. 
"--> 
"<html> 
  
    <%@include file="/WEB-INF/jspf/header.jspf" %>
          
          <h1>Registration form</h1>    
          <form method="post" action="RegistrationController">  
              <div class="form-element"> 
                  <label for="name">Name</label> 
                  <input type="text" name="name" id="name" size="40"> 
              </div> 
              <div class="form-element"> 
                  <label for="username">Username</label> 
                  <input type="text" name="uname" id="uname"> 
              </div> 
              <div class="form-element"> 
                  <label for="password">Password</label> 
                  <input type="password" name="password" id="password"> 
              </div> 
              <div class="form-element"> 
                  <label for="rPassword">Repeat password</label> 
                  <input type="password" name="rPassword" id="rPassword"> 
              </div> 
              <div class="form-element"> 
                  <label for="email">Email</label> 
                  <input type="email" id="email" name="email"> 
              </div> 
              <div class="form-element"> 
                  <label for="gender">Gender</label> 
                  Male<input type="radio" name="gender" value="male">  
                  Female<input type="radio" name="gender" value="female">  
              </div> 
              <div class="form-element"> 
                  <label for="tel">Telephone</label> 
                  <input type="tel" name="tel" id="tel"> 
              </div> 
              <div class="form-element"> 
                  <label for="country">Country</label> 
                  <select name="country" id="country"> 
                      <option>Romania</option> 
                      <option>Italy</option> 
                      <option>France</option> 
                      <option>Germany</option> 
                  </select> 
              </div> 
              <div class="form-element"> 
                  <label for="spam">Subscribe to maillist</label> 
                  <input type="checkbox" name="spam" id="spam">
                </div>  
                  <div class="form-element"> 
                      <input type="submit" value="Submit"> 
                    <input type="reset" value="Reset"> 
                </div>   
                       
                     
            </form> 
        </div>
        
        <c:if test ="${not empty requestScope.errors}" var="errors" >
                    
                <c:forEach items="${requestScope.errors}" var="errors"
                varStatus="err"
                begin="0" step="1">
                ${errors}</br>
                </c:forEach>       
                        
        </c:if>
        
        
      
             </body>

              <%@include file="/WEB-INF/jspf/footer.jspf" %>  </html>
