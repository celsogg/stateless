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
@Table(name = "plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
    @NamedQuery(name = "Plan.findByIdPlan", query = "SELECT p FROM Plan p WHERE p.idPlan = :idPlan"),
    @NamedQuery(name = "Plan.findByNombrePlan", query = "SELECT p FROM Plan p WHERE p.nombrePlan = :nombrePlan"),
    @NamedQuery(name = "Plan.findByAnioPlan", query = "SELECT p FROM Plan p WHERE p.anioPlan = :anioPlan"),
    @NamedQuery(name = "Plan.findByCodigoPlan", query = "SELECT p FROM Plan p WHERE p.codigoPlan = :codigoPlan"),
    @NamedQuery(name = "Plan.findByVisiblePlan", query = "SELECT P FROM Plan p WHERE p.visiblePlan = 1")})
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PLAN")
    private Integer idPlan;
    @Size(max = 128)
    @Column(name = "NOMBRE_PLAN")
    private String nombrePlan;
    @Column(name = "ANIO_PLAN")
    private Integer anioPlan;
    @Size(max = 32)
    @Column(name = "CODIGO_PLAN")
    private String codigoPlan;
    @Column(name = "VISIBLE_PLAN")
        private Boolean visiblePlan = false;
    @JoinColumn(name = "ID_CARRERA", referencedColumnName = "ID_CARRERA")
    @ManyToOne
    private Carrera idCarrera;
    
    @OneToMany(mappedBy = "idPlan")
    private List<Asignatura> asignaturaCollection;

    public Plan() {
    }

    public Plan(Integer idPlan) {
        this.idPlan = idPlan;
    }
    
    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public Integer getAnioPlan() {
        return anioPlan;
    }

    public void setAnioPlan(Integer anioPlan) {
        this.anioPlan = anioPlan;
    }

    public String getCodigoPlan() {
        return codigoPlan;
    }

    public void setCodigoPlan(String codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Boolean getVisiblePlan() {
        return visiblePlan;
    }
    
    public String getVisiblePlanString() {
        return ( visiblePlan != null && visiblePlan )? "Si" : "No";
    }

    public void setVisiblePlan(Boolean visiblePlan) {
        this.visiblePlan = visiblePlan;
    }
    
    

    @XmlTransient
    public List<Asignatura> getAsignaturaCollection() {
        return asignaturaCollection;
    }

    public void setAsignaturaCollection(Collection<Asignatura> asignaturaCollection) {
        this.asignaturaCollection = (List<Asignatura>) asignaturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.idPlan == null && other.idPlan != null) || (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Plan[ idPlan=" + idPlan + " ]";
    }
    
}
