package diginamic.lightRh.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import diginamic.lightRh.dtos.AbsencesDto;
import diginamic.lightRh.services.AbsenceService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/absences")
@Validated
@RequiredArgsConstructor
public class AbsencesController {

	private final AbsenceService absenceService;

	@PostMapping("createAbsence")
	public ResponseEntity<Map<String, Object>> newAbsence(@RequestBody AbsencesDto absencesReq) {
		try {

			// Creating optionals objects can maybe null
			Optional<String> motifOptional = Optional.ofNullable(absencesReq.getMotif());
			Optional<String> labelOptional = Optional.ofNullable(absencesReq.getLabel());

			absenceService.createAbsenceForEmployee(

					absencesReq.getEmail(), 
					absencesReq.getDateStart(), 
					absencesReq.getDateEnd(), 
					absencesReq.getType(),
					motifOptional, 
					labelOptional
					);
			
			Map<String, Object> response = new HashMap<>();
			response.put("Message : ", "Absence created successfully.");
			
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to create absence"));
		}
	}

	@PutMapping("updateAbsence/{id}")
	public ResponseEntity<Map<String, Object>> updateAbsence(@PathVariable int id, @RequestBody AbsencesDto absencesReq) {
		try {
			
			// Creating optionals objects can maybe null
			Optional<String> motifOptional = Optional.ofNullable(absencesReq.getMotif());
			Optional<String> labelOptional = Optional.ofNullable(absencesReq.getLabel());
			
			absenceService.updateAbsenceForEmployee(
					id,
					absencesReq.getEmail(), 
					absencesReq.getDateStart(), 
					absencesReq.getDateEnd(), 
					absencesReq.getType(),
					motifOptional, 
					labelOptional
					);

			Map<String, Object> response = new HashMap<>();
			response.put("Message : ", "Absence created successfully.");
		  return ResponseEntity.status(HttpStatus.CREATED).body(response);
	  }
	  catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to create absence"));
	    }
  }
}
