package diginamic.lightRh.controllers;

import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import diginamic.lightRh.entities.Absence;
import diginamic.lightRh.services.AbsenceService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/employee")
@Validated
@RequiredArgsConstructor
public class EmployeeController {

    private final AbsenceService absenceService;

    // Get all absences for a specific employee
    @GetMapping("/absences")
    public ResponseEntity<Collection<Absence>> getAllAbsencesForEmployee(@RequestParam String email) {
        try {
            Collection<Absence> absences = absenceService.getAllAbsencesForEmployee(email);
            return new ResponseEntity<>(absences, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


