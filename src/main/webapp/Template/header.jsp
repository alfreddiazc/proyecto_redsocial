<%-- 
    Document   : header
    Created on : 24/04/2020, 10:20:41 PM
    Author     : USUARIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

	<header>

		<div class="container">
			<div class="row shadow  mb-5 bg-light rounded">
				<div class="col-md-2">
					<img class="img-thumbnail" src="img/A.png" alt="" width="150px" height="150px">
				</div>
				<div class="textBotom col-md-3">
					<h1>alfredo</h1>
				</div>
				<div class="col-md-7 ">
					<div class="container textBotom2 ">
						<div class="dropdown dropleft float-right ">
							<button type="button" class="btn  dropdown-toggle" data-toggle="dropdown">
								<img class="img-thumbnail rounded-circle" src="img/icono.png" alt="">
							</button>
							<div class="dropdown-menu border-info shadow " style="width: 300px;">
								<a class="dropdown-item " href="home.jsp">Perfil</a>
								<a class="dropdown-item " href="siguiendo.jsp">Siguiendo</a>
								<a class="dropdown-item" href="seguidores.jsp">Seguidores</a>
                                                                <hr>
                                                                <a class="dropdown-item " href="#">Configuracion</a>
                                                                <hr>
								<a class="dropdown-item " href="#">Publico</a>
								<a class="dropdown-item" href="index.html">Cerrar Sesion</a>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="clearfix"></div>
	</header>
