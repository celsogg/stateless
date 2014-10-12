/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matias
 */
@Entity
@Table(name = "programa_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramaAsignatura.findAll", query = "SELECT p FROM ProgramaAsignatura p"),
    @NamedQuery(name = "ProgramaAsignatura.findByIdPrograma", query = "SELECT p FROM ProgramaAsignatura p WHERE p.idPrograma = :idPrograma"),
    @NamedQuery(name = "ProgramaAsignatura.findByAnioPrograma", query = "SELECT p FROM ProgramaAsignatura p WHERE p.anioPrograma = :anioPrograma"),
    @NamedQuery(name = "ProgramaAsignatura.findBySemestrePrograma", query = "SELECT p FROM ProgramaAsignatura p WHERE p.semestrePrograma = :semestrePrograma")})
public class ProgramaAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROGRAMA")
    private Integer idPrograma;
    @Column(name = "ANIO_PROGRAMA")
    private Integer anioPrograma;
    @Column(name = "SEMESTRE_PROGRAMA")
    private Integer semestrePrograma;
    @JoinColumn(name = "ID_ASIGNATURA", referencedColumnName = "ID_ASIGNATURA")
    @ManyToOne
    private Asignatura idAsignatura;

    public ProgramaAsignatura() {
    }

    public ProgramaAsignatura(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Integer getAnioPrograma() {
        return anioPrograma;
    }

    public void setAnioPrograma(Integer anioPrograma) {
        this.anioPrograma = anioPrograma;
    }

    public Integer getSemestrePrograma() {
        return semestrePrograma;
    }

    public void setSemestrePrograma(Integer semestrePrograma) {
        this.semestrePrograma = semestrePrograma;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrograma != null ? idPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramaAsignatura)) {
            return false;
        }
        ProgramaAsignatura other = (ProgramaAsignatura) object;
        if ((this.idPrograma == null && other.idPrograma != null) || (this.idPrograma != null && !this.idPrograma.equals(other.idPrograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProgramaAsignatura[ idPrograma=" + idPrograma + " ]";
    }
    
}
