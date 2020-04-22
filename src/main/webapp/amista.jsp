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
        
        <%!Shadiagram s;
        Usuario u=s.buscar2("victor");

        List<Amistad> a=u.getAmistadList();
        %>
        
        <%for (Amistad as : a) {
              %> <h1> <%=as.getAmistad()%></h1> 
              <%
            }
        %>
        
        
    </body>
</html>
