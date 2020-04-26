<%-- 
    Document   : imagen
    Created on : 22/04/2020, 02:33:00 PM
    Author     : USUARIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Subir archivos</h1>
    <form method="post" action="imagen" enctype="multipart/form-data">
    <fieldset>
       <legend>Formulario de subida</legend>
       <input type="file" name="files" id="files" multiple/>
       <button type="submit">Subir</button>   
    </fieldset>
</form>
    </body>
</html>
