package com.mvc.dao;

import java.util.List;

import com.mvc.bean.Tipousuario;

public interface TipousuarioDao {
	public Tipousuario selectById(String tiposuario);

	public List<Tipousuario> selectAll();

	public void insert(Tipousuario tipousuario);
	
	public void update(Tipousuario tipousuario);

	public void delete(Tipousuario tipousuario);
}
