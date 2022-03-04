package co.chu3la.legume.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.chu3la.legume.entities.Role;
import co.chu3la.legume.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
    Optional<Role> findByName(RoleName name);
    
    Optional<Role> findById(Long id);
    
    List<Role> findAll();
    
    
}
