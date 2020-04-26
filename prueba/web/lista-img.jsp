<%-- 
    Document   : lisyta-img
    Created on : 01/03/2019, 02:13:12 PM
    Author     : jmendez
--%>

<%@page import="VO.ImagenVO"%>
<%@page import="DAO.ImagenDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    
        <h1>Subir Imagenes :)</h1>
    

    <%
        ImagenDAO emp = new ImagenDAO();
        ImagenVO imgvo = new ImagenVO();
        ArrayList<ImagenVO> listar = emp.Listar_ImagenVO();
    %>

    <table border="1">
     
        <a id="mostrar" href="ControllerImagen?action=insert&id=<%=imgvo.getCodigoimg()%>">Nuevo Registro</a>
        <thead>
            <tr>
                <th>Codigo</th>
                <th>Nombre</th>
                <th>Imagen</th>
                <th>Accion</th>
            </tr>
        </thead>
        <tbody>
            
            
            <%if (listar.size() > 0) {
                        for (ImagenVO listar2 : listar) {
                            imgvo = listar2;
                %>
                <tr>
                    <td><%=imgvo.getCodigoimg()%></td>
                    <td><%=imgvo.getNombreimg()%></td>
                    <td>
                        <%
                            if (imgvo.getArchivoimg2() != null) {
                        %>
                        <a href="imagen?id=<%=imgvo.getCodigoimg() %>" target="_blank"><img src="img/img.png" style="width: 20px;height: 20px"/> 
                        <%
                            } else {
                                out.print("No disponible");
                            }
                        %>
                    </td>
                    <td>
                       
                        <a href="ControllerImagen?action=edit&id=<%=imgvo.getCodigoimg()%>"> Modificar</a> --
                        <a href="ControllerImagen?action=delete&id=<%=imgvo.getCodigoimg()%>"> Eliminar</a>
                    </td>
                </tr>
                <%}
                    }%>

        </tbody>
    </table>


</body>
</html>
