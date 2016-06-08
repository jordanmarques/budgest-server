package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.model.Invitation;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    @Query("select i from Invitation i where personId = :personId")
    List<Invitation> findByPersonId(@Param("personId") String personId);

    @Query("select i from Invitation i where personId = :personId AND eventId = :eventId")
    Invitation findByEventIdAndPersonId(@Param("personId") String personId, @Param("eventId") String eventId);
}