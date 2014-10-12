/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matias
 */
@Entity
@Table(name = "habilidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habilidad.findAll", query = "SELECT h FROM Habilidad h"),
    @NamedQuery(name = "Habilidad.findByIdHabilidad", query = "SELECT h FROM Habilidad h WHERE h.idHabilidad = :idHabilidad"),
    @NamedQuery(name = "Habilidad.findByDescripcionHabilidad", query = "SELECT h FROM Habilidad h WHERE h.descripcionHabilidad = :descripcionHabilidad"),
    @NamedQuery(name = "Habilidad.findByGradoHabilidad", query = "SELECT h FROM Habilidad h WHERE h.gradoHabilidad = :gradoHabilidad")})
public class Habilidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HABILIDAD")
    private Integer idHabilidad;
    @Size(max = 1024)
    @Column(name = "DESCRIPCION_HABILIDAD")
    private String descripcionHabilidad;
    @Column(name = "GRADO_HABILIDAD")
    private Integer gradoHabilidad;
    @JoinTable(name = "asignatura_habilidad", joinColumns = {
        @JoinColumn(name = "ID_HABILIDAD", referencedColumnName = "ID_HABILIDAD")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIGNATURA", referencedColumnName = "ID_ASIGNATURA")})
    @ManyToMany
    private Collection<Asignatura> asignaturaCollection;

    public Habilidad() {
    }

    public Habilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public Integer getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getDescripcionHabilidad() {
        return descripcionHabilidad;
    }

    public void setDescripcionHabilidad(String descripcionHabilidad) {
        this.descripcionHabilidad = descripcionHabilidad;
    }

    public Integer getGradoHabilidad() {
        return gradoHabilidad;
    }

    public void setGradoHabilidad(Integer gradoHabilidad) {
        this.gradoHabilidad = gradoHabilidad;
    }

    @XmlTransient
    public Collection<Asignatura> getAsignaturaCollection() {
        return asignaturaCollection;
    }

    public void setAsignaturaCollection(Collection<Asignatura> asignaturaCollection) {
        this.asignaturaCollection = asignaturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHabilidad != null ? idHabilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habilidad)) {
            return false;
        }
        Habilidad other = (Habilidad) object;
        if ((this.idHabilidad == null && other.idHabilidad != null) || (this.idHabilidad != null && !this.idHabilidad.equals(other.idHabilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Habilidad[ idHabilidad=" + idHabilidad + " ]";
    }
    
}
