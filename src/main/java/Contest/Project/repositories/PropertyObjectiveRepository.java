package Contest.Project.repositories;

import Contest.Project.entities.PropertyObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyObjectiveRepository extends JpaRepository<PropertyObjective, Integer> {

}
