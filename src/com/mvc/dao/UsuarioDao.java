package com.mvc.dao;

import java.util.List;
import com.mvc.bean.Usuario;

public interface UsuarioDao {
	public Usuario selectById(String usuario);

	public List<Usuario> selectAll();

	public void insert(Usuario usuario);
	
	public void update(Usuario usuario);

	public void delete(Usuario usuario);
}
