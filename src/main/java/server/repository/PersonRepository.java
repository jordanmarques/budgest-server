package server.repository;

import lombok.experimental.PackagePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p where (pseudo = :id or mail = :id) and password = :password")
    List<Person> login(@Param("id") String id, @Param("password") String password);

    @Query("select p from Person p where mail = :mail")
    List<Person> findByMailAdress(@Param("mail") String mail);

    @Query("select p from Person p where pseudo = :pseudo")
    List<Person> findByPseudo(@Param("pseudo") String pseudo);

}
