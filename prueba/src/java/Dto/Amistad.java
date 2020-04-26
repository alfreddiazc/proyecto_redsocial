/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pc-Victor
 */
@Entity
@Table(name = "amistad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amistad.findAll", query = "SELECT a FROM Amistad a")
    , @NamedQuery(name = "Amistad.findById", query = "SELECT a FROM Amistad a WHERE a.id = :id")
    , @NamedQuery(name = "Amistad.findByFechasolicitud", query = "SELECT a FROM Amistad a WHERE a.fechasolicitud = :fechasolicitud")
    , @NamedQuery(name = "Amistad.findByEstado", query = "SELECT a FROM Amistad a WHERE a.estado = :estado")})
public class Amistad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechasolicitud")
    @Temporal(TemporalType.DATE)
    private Date fechasolicitud;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "amistad", referencedColumnName = "id")
    @ManyToOne
    private Usuario amistad;

    public Amistad() {
    }

    public Amistad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(Date fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getAmistad() {
        return amistad;
    }

    public void setAmistad(Usuario amistad) {
        this.amistad = amistad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amistad)) {
            return false;
        }
        Amistad other = (Amistad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dto.Amistad[ id=" + id + " ]";
    }

    public Amistad(Usuario usuario, Usuario amistad) {
        this.usuario = usuario;
        this.amistad = amistad;
    }
    
    
}
