
package th.co.grouplease.operations.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.operations.model.Operation;

import java.util.List;

/**
 * Empty JpaRepository is enough for a simple crud.
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {
    
    /* A version to fetch List instead of Page to avoid extra count query. */
    List<Operation> findAllBy(Pageable pageable);
    
    List<Operation> findByOperationCodeLikeIgnoreCase(String nameFilter);


}
