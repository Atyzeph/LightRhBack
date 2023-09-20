package diginamic.lightRh.nightTreament;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import diginamic.lightRh.entities.Absence;
import diginamic.lightRh.enums.AbsenceStatusEnum;
import diginamic.lightRh.repositories.AbsenceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class NightTreatmentConfiguration {
    
    private final AbsenceRepository absenceRepository;
    //Trigger the function on every 5 minutes 
    //replace the minutes by 0 to have it run at 0:00Am every day
    //including satudray, sunday, holidays etc.
    //TODO BETTER improve when the task is scheduled to ignore specific days if possible
    //FIXME Set the scheduled to an appropriate time once implementation is valid
    @Scheduled(cron = "0 0 20 * * *")
    @Transactional(rollbackOn = Exception.class)
    public void doNightTreatment() {
	Optional<List<Absence>> absencesWithInitalStatus = absenceRepository.findAllByStatusOrderByDateStart(AbsenceStatusEnum.INITIALE);
	
	System.out.println("night treatment launched");
	if(!absencesWithInitalStatus.isEmpty()) {
	    absencesWithInitalStatus.get().forEach(absence -> System.out.println(absence.getId()+ " "+absence.getDateStart()+" "+absence.getStatus()));
	    //TODO continue night treatment buisness implementation
	}
    }

}
