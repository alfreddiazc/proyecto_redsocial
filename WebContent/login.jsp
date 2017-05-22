<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<title>Inicio de Sesion</title>
</head>
<body>

<h1>Login de Usuario</h1>

<form role="form" action="LoginServlet" method="post">
      <div class="form-group"> <!-- Full Name -->
        <label for="usuario" class="control-label">Usuario</label>
        <input type="text" class="form-control" id="usuario" name="usuario" placeholder="crangarita">
    </div>    

    <div class="form-group"> <!-- Street 1 -->
        <label for="clave" class="control-label">Clave</label>
        <input type="password" class="form-control" id="clave" name="clave" placeholder="Utilizar letras mayusculas, minusculas, numeros y simbolos">
    </div> 
  <div class="form-group"> <!-- Submit Button -->
        <button type="submit" class="btn btn-primary">Ingresar</button>
    </div>    
</form>

</body>
</html>