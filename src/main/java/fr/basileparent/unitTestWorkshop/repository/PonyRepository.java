package fr.basileparent.unitTestWorkshop.repository;

import fr.basileparent.unitTestWorkshop.model.Pony;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PonyRepository extends CrudRepository<Pony, Integer> {

    @Query("SELECT p FROM Pony p WHERE p.age > 10")
    List<Pony> findTooOldPonies();

    @Query("SELECT p FROM Pony p WHERE p.age > :age")
    List<Pony> findPoniesOlderThan(@Param("age") Integer age);

    Pony findByName(String name);

    List<Pony> findAll();

}
