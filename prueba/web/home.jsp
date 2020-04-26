<%-- 
    Document   : home2
    Created on : 25/04/2020, 12:29:37 AM
    Author     : USUARIO
--%>

<%@page import="Dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="VO.ImagenVO"%>
<%@page import="Dao.ImagenDAO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>redsocial</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>
        <%@include file="Template/header.jsp" %>
        <% Usuario nom = (Usuario) request.getSession().getAttribute("nom"); %>




        <div class="container">
            <div class="row py-2">
                <div class="col-md-6 ">
                    <form class="form-inline" action="buscarUsuario"  method="POST" >
                        <input class="form-control mr-sm-4" type="text" placeholder="Search" name="buscar">


                        <button class="btn btn-outline-info " type="submit" >Buscar...</button>
                    </form>
                </div>
                <div>
                    <h2>Cargar Imagenes</h2>

                    <%
                        ImagenDAO emp = new ImagenDAO();
                        ImagenVO imgvo = new ImagenVO();
                        ArrayList<ImagenVO> listar = emp.Listar_ImagenVO();
                    %>
                    <a id="mostrar" href="ControllerImagen?action=insert&id=<%=imgvo.getCodigoimg()%>">Nuevo Registro</a>
                </div>
                <div class="col-md-6 " style="font-size: 2em;">Mis fotos</div>

            </div>
        </div>
        <div class="container">
            <section class="main row shadow p-4 mb-4">
                <article class="col-sm-8 col-md-9">

                    <h1> <%= u.getUsuario()%></h1>
                    <h1> <%= u.getApellido()%></h1>
                    <h1> <%= u.getEmail()%></h1>
                    <h1> <%= u.toString()%></h1>
                    <hr>



                </article>
            </section>

            <div class="clearfix"></div>
        </div>
        <%@include file="Template/footer.jsp" %>
    </body>
</html>
