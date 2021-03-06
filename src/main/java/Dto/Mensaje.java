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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findById", query = "SELECT m FROM Mensaje m WHERE m.id = :id"),
    @NamedQuery(name = "Mensaje.findByFechamensaje", query = "SELECT m FROM Mensaje m WHERE m.fechamensaje = :fechamensaje")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechamensaje")
    @Temporal(TemporalType.DATE)
    private Date fechamensaje;
    @Lob
    @Size(max = 65535)
    @Column(name = "mensaje")
    private String mensaje;
    @JoinColumn(name = "envia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario envia;
    @JoinColumn(name = "recibe", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario recibe;

    public Mensaje() {
    }

    public Mensaje(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechamensaje() {
        return fechamensaje;
    }

    public void setFechamensaje(Date fechamensaje) {
        this.fechamensaje = fechamensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getEnvia() {
        return envia;
    }

    public void setEnvia(Usuario envia) {
        this.envia = envia;
    }

    public Usuario getRecibe() {
        return recibe;
    }

    public void setRecibe(Usuario recibe) {
        this.recibe = recibe;
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
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dto.Mensaje[ id=" + id + " ]";
    }
    
}
