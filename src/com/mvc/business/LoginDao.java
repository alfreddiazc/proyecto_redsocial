package com.mvc.business;

import com.mvc.bean.Usuario;
import com.mvc.dao.HibernateUsuarioDao;

public class LoginDao {
	public String authenticateUser(Usuario usuario){
		
		HibernateUsuarioDao usuarioDao = new HibernateUsuarioDao();
		
		Usuario u = usuarioDao.selectById(usuario.getUsuario());
		
		if (u != null) {
			if (u.getClave().contentEquals(usuario.getClave())) {
				return "SUCCESS";
			}
		}
		
		return "ERROR";
		
	}
}
