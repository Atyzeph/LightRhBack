package diginamic.lightRh.dtos;

import java.util.Date;

import diginamic.lightRh.entities.Employee;
import diginamic.lightRh.enums.AbsenceStatusEnum;
import diginamic.lightRh.enums.AbsenceTypeEnum;

public class absencesDto {

	String email;
    Date dateStart;
    Date dateEnd;
    String motif;
    String label;
    AbsenceTypeEnum type;
    AbsenceStatusEnum status;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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

}
