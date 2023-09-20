package diginamic.lightRh.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employerrtt")
public class EmployerRtt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name="remaining_days")
    Integer remainingDays;
    public Integer getRemainingDays() {
        return remainingDays;
    }
    public void setRemainingDays(Integer remainingDays) {
        this.remainingDays = remainingDays;
    }
    public Integer getId() {
        return id;
    }
}
