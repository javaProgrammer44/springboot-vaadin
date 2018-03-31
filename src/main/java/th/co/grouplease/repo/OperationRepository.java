
package th.co.grouplease.repo;

import th.co.grouplease.model.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Empty JpaRepository is enough for a simple crud.
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {
    
    /* A version to fetch List instead of Page to avoid extra count query. */
    List<Operation> findAllBy(Pageable pageable);
    
    List<Operation> findByOperationCodeLikeIgnoreCase(String nameFilter);




    // For lazy loading and filtering
    List<Operation> findByOperationCodeLikeIgnoreCase(String nameFilter, Pageable pageable);

    long countByOperationCodeLikeIgnoreCase(String nameFilter);


}
