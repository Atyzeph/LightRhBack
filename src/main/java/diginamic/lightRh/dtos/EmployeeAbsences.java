package diginamic.lightRh.dtos;

import java.util.Collection;

import diginamic.lightRh.entities.Absence;

public class EmployeeAbsences {
    Collection<Absence> absences;
    Integer remainingRttDays;
    Integer remainingPaidLeaveDays;
    String email;
    
    public Collection<Absence> getAbsences() {
        return absences;
    }
    public void setAbsences(Collection<Absence> absences) {
        this.absences = absences;
    }
    public Integer getRemainingRttDays() {
        return remainingRttDays;
    }
    public void setRemainingRttDays(Integer remainingRttDays) {
        this.remainingRttDays = remainingRttDays;
    }
    public Integer getRemainingPaidLeaveDays() {
        return remainingPaidLeaveDays;
    }
    public void setRemainingPaidLeaveDays(Integer remainingPaidLeaveDays) {
        this.remainingPaidLeaveDays = remainingPaidLeaveDays;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public EmployeeAbsences(Collection<Absence> absences, Integer remainingRttDays, Integer remainingPaidLeaveDays,
	    String email) {
	super();
	this.absences = absences;
	this.remainingRttDays = remainingRttDays;
	this.remainingPaidLeaveDays = remainingPaidLeaveDays;
	this.email = email;
    }
    
}
