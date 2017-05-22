package com.mvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mvc.bean.Tipousuario;
import com.mvc.bean.Usuario;
import com.util.HibernateSession;

public class HibernateTipousuarioDao implements TipousuarioDao{

	@Override
	public Tipousuario selectById(String tipousuarioId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateSession.getSessionFactory();
		Session session = sessionFactory.openSession();
		Tipousuario tipousuario = (Tipousuario) session.get(Tipousuario.class, tipousuarioId);
		session.close();
		return tipousuario;
		
	}

	@Override
	public List<Tipousuario> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Tipousuario tipousuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Tipousuario tipousuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tipousuario tipousuario) {
		// TODO Auto-generated method stub
		
	}

}
