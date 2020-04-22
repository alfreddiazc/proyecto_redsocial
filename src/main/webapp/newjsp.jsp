<%-- 
    Document   : newjsp
    Created on : 21/04/2020, 06:34:02 PM
    Author     : USUARIO
--%>

<%@page import="Dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% Usuario u=(Usuario) request.getSession().getAttribute("u");%>
    <body>
        <h1><%=u.getUsuario()%>  Mk ya! </h1>
        <h1><%=u.getEmail()%>  email </h1>
        <h1><%=u.getApellido()%> apellido </h1>
        <h1><%=u.getUsuario()%>  usuario </h1>
        <hr>
        <form action="amista">
            <label for="a"> agregar amigo </label> 
            <input aria-describedby="emailHelp" name="amigo" class="form-control" id="a" placeholder="..." type="text"> 
            <input aria-describedby="emailHelp" name="usuario" class="form-control" value="<%=u.getUsuario()%>" placeholder="..." type="hidden">
            <button type="submit">agregar</button>
        </form>
            
            <a href="amista.jsp">siguiendo</a>
    </body>
</html>
