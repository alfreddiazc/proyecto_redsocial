package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.Usuario;
import com.mvc.business.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		 
		Usuario usuario = new Usuario(); //creating object for LoginBean class, which is a normal java class, contains just setters and getters. Bean classes are efficiently used in java to access user information wherever required in the application.
		 
		usuario.setUsuario(usuarioTxt); //setting the username and password through the loginBean object then only you can get it in future.
		usuario.setClave(claveTxt);
		 
		LoginDao loginDao = new LoginDao(); //creating object for LoginDao. This class contains main logic of the application.
		 
		String userValidate = loginDao.authenticateUser(usuario); //Calling authenticateUser function
		 System.out.println(userValidate);
		if(userValidate.contentEquals("SUCCESS")) {
			request.setAttribute("userName", usuarioTxt); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
			request.getRequestDispatcher("/home.jsp").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
		} else {
			request.setAttribute("errMessage", userValidate); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
			request.getRequestDispatcher("/login.jsp").forward(request, response);//forwarding the request
		}
	}

}
