<%-- 
    Document   : amista
    Created on : 21/04/2020, 07:18:22 PM
    Author     : USUARIO
--%>

<%@page import="java.util.List"%>
<%@page import="Dto.Amistad"%>
<%@page import="Negocio.Shadiagram"%>
<%@page import="Dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%  Usuario user= (Usuario) request.getSession().getAttribute("u");
        List<Amistad> la=user.getAmistadList();
        %>
        
        <%for (Amistad as : la) {
              %> <h1> <%=as.getAmistad()%></h1> 
              <%
            }
        %>
        
        
    </body>
</html>
