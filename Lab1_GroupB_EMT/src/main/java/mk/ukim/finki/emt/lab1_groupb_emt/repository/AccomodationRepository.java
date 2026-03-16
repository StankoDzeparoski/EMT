package mk.ukim.finki.emt.lab1_groupb_emt.repository;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {
}
