package com.mvc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "tipousuario")

public class Tipousuario implements Serializable {
	
	@Id
	@Column (name = "id")
	String id;
	
	@Column (name = "descripcion")
	String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER, 
			mappedBy = "tipousuario")
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Tipousuario() {

		// TODO Auto-generated constructor stub
	}
	public Tipousuario(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	
}
