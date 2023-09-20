package diginamic.lightRh.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import diginamic.lightRh.entities.Absence;
import diginamic.lightRh.enums.AbsenceStatusEnum;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer>{
    
    Optional<Absence> findById(Integer id);
    List<Absence> findAll();
    Optional<List<Absence>> findAllByStatusOrderByDateStart(AbsenceStatusEnum absenceStatus);
    List<Absence> findByEmployeeEmail(String email);
}