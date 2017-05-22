package com.mvc.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table (name="adicional")
public class Adicional implements Serializable {
	
	@Id
	@Column(name="usuarioid")
	private String usuarioid;
	
	private Date fechanac;
	private String lugarnac;
	
	@OneToOne (fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Usuario usuario;
	
	
	public Adicional() {
		// TODO Auto-generated constructor stub
	}

	public Adicional(String usuarioid, Date fechanac, String lugarnac, Usuario usuario) {
		this.usuarioid = usuarioid;
		this.fechanac = fechanac;
		this.lugarnac = lugarnac;
		this.usuario = usuario;
	}
	
	public String getUsuarioid() {
		return usuarioid;
	}
	public void setUsuarioid(String usuarioid) {
		this.usuarioid = usuarioid;
	}
	public Date getFechanac() {
		return fechanac;
	}
	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}
	public String getLugarnac() {
		return lugarnac;
	}
	public void setLugarnac(String lugarnac) {
		this.lugarnac = lugarnac;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
