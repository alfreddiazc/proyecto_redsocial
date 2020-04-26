<%-- 
    Document   : img
    Created on : 01/03/2019, 02:13:23 PM
    Author     : jmendez
--%>
<%@page import="VO.ImagenVO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Integer dato = 0;
            try {
                ImagenVO img = (ImagenVO) request.getAttribute("row");
                dato = img.getCodigoimg();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            boolean icono = false;
            try {
                icono = (Boolean) request.getAttribute("row2");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        %>
        
        <form name="formimg" action="ControllerImagen" method="post" enctype="multipart/form-data">
           
          
                <table border="1">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Campo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><label for="id">Nombre del archivo: </label>
                            </td>  
                            <td>
                                <input type="text" name="txtname" value="<c:out value="${row.nombreimg}" />"/>
                            </td>
                        </tr>
                       
                        <tr class="alt">
                            <td>
                                <label for="id">Seleccionar Imagen: *</label> 
                                <%
                                    if (icono) {
                                %>
                                <a href="imagen?id=<%out.print(dato); %>" target="_blank"> Ver Imagen</a>
                                <%
                                    } else {
                                        out.print("Sin Imagen");
                                    }
                                %>
                            </td>  
                            <td>
                                <input type="file" name="fichero" value="" id="btn" class="btn" />
                            </td>
                        </tr>
                        
                        <tr>
                            <td colspan="2" style="text-align: center"><input type="submit" value="Enviar Archivo" name="submit" id="btn" class="btn"/></td>
                        </tr>
                        
                    </tbody>
                </table>

        </form>
        <a href="lista-img.jsp">Regresar</a>
        
        
   
    </body>
</html>
