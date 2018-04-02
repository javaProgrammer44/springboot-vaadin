
package th.co.grouplease.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.model.Operation;
import th.co.grouplease.model.OperationArea;

import java.util.List;

/**
 * Empty JpaRepository is enough for a simple crud.
 */
public interface OperationAreaRepository extends JpaRepository<OperationArea, Long> {
    
    /* A version to fetch List instead of Page to avoid extra count query. */
    List<OperationArea> findAllBy(Pageable pageable);
    
    List<OperationArea> findByProvinceAreaLikeIgnoreCase(String nameFilter);




    // For lazy loading and filtering
    List<OperationArea> findByProvinceAreaLikeIgnoreCase(String nameFilter, Pageable pageable);

    long countByProvinceAreaLikeIgnoreCase(String nameFilter);


}
