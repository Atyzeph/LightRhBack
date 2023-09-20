package diginamic.lightRh.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import diginamic.lightRh.enums.AbsenceStatusEnum;
import diginamic.lightRh.enums.AbsenceTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="absence")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="date_start")
    private Date dateStart;
    @Column(name="date_end")
    private Date dateEnd;
    private String motif;
    private String label;
    @Enumerated(EnumType.STRING)
    private AbsenceTypeEnum type;
    @Enumerated(EnumType.STRING)
    private AbsenceStatusEnum status;
    @ManyToOne
    @JoinColumn(name="employee_id") // Good column in DB
    @JsonIgnore
    private Employee employee;
    
    public Date getDateStart() {
        return dateStart;
    }
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    public Date getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public Integer getId() {
        return id;
    }    
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public AbsenceTypeEnum getType() {
        return type;
    }
    public void setType(AbsenceTypeEnum type) {
        this.type = type;
    }
    public AbsenceStatusEnum getStatus() {
        return status;
    }
    public void setStatus(AbsenceStatusEnum status) {
        this.status = status;
    }
    public Absence() {
	super();
    }
    public Absence(Employee employee, Date dateStart, Date dateEnd, String motif, String label, AbsenceTypeEnum type) {
	    this.employee = employee;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.motif = motif;
		this.label = label;
		this.type = type;
		this.status = AbsenceStatusEnum.INITIALE;
    }
    
    
}
