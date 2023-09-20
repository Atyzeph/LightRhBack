package diginamic.lightRh.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import diginamic.lightRh.entities.Absence;
import diginamic.lightRh.entities.Employee;
import diginamic.lightRh.enums.AbsenceTypeEnum;
import diginamic.lightRh.exceptions.ConflictWithExistingAbsenceException;
import diginamic.lightRh.exceptions.InvalidDateRangeException;
import diginamic.lightRh.repositories.AbsenceRepository;
import diginamic.lightRh.repositories.EmployeeRepository;
import diginamic.lightRh.util.DateRangeValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbsenceService {

    private final AbsenceRepository absenceRepository;
    @Autowired
    private final EmployeeRepository EmployeeRepository;
    
    @Transactional(rollbackOn = Exception.class)
    public void createAbsenceForEmployee(String email, Date startDate, Date endDate, AbsenceTypeEnum type, Optional<String> motif, Optional<String> label) {

    	// Parameters validation
        Objects.requireNonNull(email, "L'email de l'Employee ne peut pas être nul.");
        Objects.requireNonNull(startDate, "La date de début ne peut pas être nulle.");
        Objects.requireNonNull(endDate, "La date de fin ne peut pas être nulle.");

        // Obtaining option's values
        String absenceMotif = motif.orElse("");
        String absenceLabel = label.orElse("Absence");
        
		Optional<Employee> opEmployee = EmployeeRepository.findByEmail(email);
        // opEmployee can not be nul for this line
		Employee employee = opEmployee.get();

		// Dates validation
		if (startDate.after(endDate)) {
			throw new InvalidDateRangeException("La date de début doit être antérieure ou égale à la date de fin.");
		}
		if(!checkAbsencePeriod(email, startDate, endDate)) {
			throw new ConflictWithExistingAbsenceException("Cette date entre en conflit avec une date déjà existante.");
		}
		
        // Creating absence
		Absence absence = new Absence(employee, startDate, endDate, absenceMotif, absenceLabel, type);
		employee.getAbsences().add(absence);
        
        absenceRepository.save(absence);
    }
    
    // Check if the period is not duplicated
    public boolean checkAbsencePeriod(String email, Date startDate, Date endDate) {
    	
    	Collection<Absence> existingAbsences = getAllAbsencesForEmployee(email);
    		
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		DateRangeValidator checker = new DateRangeValidator(startDate, endDate);

    	for (Absence absence : existingAbsences) {
    			
			// From DB
			Date dateStart = absence.getDateStart();
			String strFormattedDbDateStart = sdf.format(dateStart);
			
			Date dateEnd = absence.getDateEnd();
			String strFormattedDbDateEnd = sdf.format(dateEnd);
			
			try {
				Date formattedStartDateDb = sdf.parse(strFormattedDbDateStart);
				Date formattedEndDateDb = sdf.parse(strFormattedDbDateEnd);

				if (checker.isWithinRange(formattedStartDateDb) && checker.isWithinRange(formattedEndDateDb)) {

					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return true;
    }

	// Get all absences for a single employee
    public Collection<Absence> getAllAbsencesForEmployee(String email) {
        Collection<Absence> absences = absenceRepository.findByEmployeeEmail(email);
        return absences;
    }
}
