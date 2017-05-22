package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.Tipousuario;
import com.mvc.bean.Usuario;
import com.mvc.dao.HibernateTipousuarioDao;
import com.mvc.dao.HibernateUsuarioDao;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String usuarioTxt = request.getParameter("usuario");
		String claveTxt = request.getParameter("clave");
		String nombreTxt = request.getParameter("nombre");
		String direccionTxt = request.getParameter("direccion");
		String telefonoTxt = request.getParameter("telefono");
		String correoTxt = request.getParameter("email");
		String tipo = request.getParameter("tipo");
		
		HibernateTipousuarioDao tipousuarioDao = new HibernateTipousuarioDao();
		System.out.println(tipo);	
		Tipousuario tu = tipousuarioDao.selectById(tipo);
		System.out.println(tipo);	
		System.out.println(tu.getDescripcion());		
		Usuario usuario = new Usuario(); //creating object for LoginBean class, which is a normal java class, contains just setters and getters. Bean classes are efficiently used in java to access user information wherever required in the application.
		 
		usuario.setUsuario(usuarioTxt); //setting the username and password through the loginBean object then only you can get it in future.
		usuario.setClave(claveTxt);
		usuario.setCorreo(correoTxt);
		usuario.setDireccion(direccionTxt);
		usuario.setNombre(nombreTxt);
		usuario.setTelefono(telefonoTxt);
		usuario.setTipousuario(tu);
		
		System.out.println("Aca prueba");
		
		HibernateUsuarioDao usuarioDao = new HibernateUsuarioDao(); //creating object for LoginDao. This class contains main logic of the application.
		usuarioDao.insert(usuario);
		
		request.getRequestDispatcher("/login.jsp").forward(request, response);//forwarding the request

		 
	}

}
