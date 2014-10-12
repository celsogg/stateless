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
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.findByIdProfesor", query = "SELECT p FROM Profesor p WHERE p.idProfesor = :idProfesor"),
    @NamedQuery(name = "Profesor.findByUsuarioProfesor", query = "SELECT p FROM Profesor p WHERE p.usuarioProfesor = :usuarioProfesor"),
    @NamedQuery(name = "Profesor.findByNombreProfesor", query = "SELECT p FROM Profesor p WHERE p.nombreProfesor = :nombreProfesor"),
    @NamedQuery(name = "Profesor.findByApellidoProfesor", query = "SELECT p FROM Profesor p WHERE p.apellidoProfesor = :apellidoProfesor"),
    @NamedQuery(name = "Profesor.findByContrasenaProfesor", query = "SELECT p FROM Profesor p WHERE p.contrasenaProfesor = :contrasenaProfesor")})
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROFESOR")
    private Integer idProfesor;
    @Size(max = 32)
    @Column(name = "USUARIO_PROFESOR")
    private String usuarioProfesor;
    @Size(max = 16)
    @Column(name = "NOMBRE_PROFESOR")
    private String nombreProfesor;
    @Size(max = 16)
    @Column(name = "APELLIDO_PROFESOR")
    private String apellidoProfesor;
    @Size(max = 128)
    @Column(name = "CONTRASENA_PROFESOR")
    private String contrasenaProfesor;
    @JoinTable(name = "profesor_asignatura", joinColumns = {
        @JoinColumn(name = "ID_PROFESOR", referencedColumnName = "ID_PROFESOR")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIGNATURA", referencedColumnName = "ID_ASIGNATURA")})
    @ManyToMany
    private Collection<Asignatura> asignaturaCollection;

    public Profesor() {
    }

    public Profesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getUsuarioProfesor() {
        return usuarioProfesor;
    }

    public void setUsuarioProfesor(String usuarioProfesor) {
        this.usuarioProfesor = usuarioProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    public String getContrasenaProfesor() {
        return contrasenaProfesor;
    }

    public void setContrasenaProfesor(String contrasenaProfesor) {
        this.contrasenaProfesor = contrasenaProfesor;
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
        hash += (idProfesor != null ? idProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.idProfesor == null && other.idProfesor != null) || (this.idProfesor != null && !this.idProfesor.equals(other.idProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Profesor[ idProfesor=" + idProfesor + " ]";
    }
    
}
