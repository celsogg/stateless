/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matias
 */
@Entity
@Table(name = "perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
    @NamedQuery(name = "Perfil.findByIdPerfil", query = "SELECT p FROM Perfil p WHERE p.idPerfil = :idPerfil"),
    @NamedQuery(name = "Perfil.findByNombrePerfil", query = "SELECT p FROM Perfil p WHERE p.nombrePerfil = :nombrePerfil"),
    @NamedQuery(name = "Perfil.findByVersionPerfil", query = "SELECT p FROM Perfil p WHERE p.versionPerfil = :versionPerfil"),
    @NamedQuery(name = "Perfil.findByDescripcionPerfil", query = "SELECT p FROM Perfil p WHERE p.descripcionPerfil = :descripcionPerfil")})
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERFIL")
    private Integer idPerfil;
    @Size(max = 128)
    @Column(name = "NOMBRE_PERFIL")
    private String nombrePerfil;
    @Size(max = 32)
    @Column(name = "VERSION_PERFIL")
    private String versionPerfil;
    @Size(max = 2048)
    @Column(name = "DESCRIPCION_PERFIL")
    private String descripcionPerfil;
    @JoinColumn(name = "ID_CARRERA", referencedColumnName = "ID_CARRERA")
    @ManyToOne
    private Carrera idCarrera;
    @OneToMany(mappedBy = "idPerfil")
    private List<Seccion> seccionCollection;

    public Perfil() {
    }

    public Perfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getVersionPerfil() {
        return versionPerfil;
    }

    public void setVersionPerfil(String versionPerfil) {
        this.versionPerfil = versionPerfil;
    }

    public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    @XmlTransient
    public List<Seccion> getSeccionCollection() {
        return seccionCollection;
    }

    public void setSeccionCollection(Collection<Seccion> seccionCollection) {
        this.seccionCollection = (List<Seccion>)seccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Perfil[ idPerfil=" + idPerfil + " ]";
    }
    
}
