package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

}