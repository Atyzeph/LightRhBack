package diginamic.lightRh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diginamic.lightRh.entities.PublicHoliday;

@Repository
public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Integer>{
    
}
