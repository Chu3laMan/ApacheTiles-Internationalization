package co.chu3la.legume.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.chu3la.legume.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByUserInfoId(Long id);

//    Optional<User> findByUsernameOrEmail(String username, String email);
    
    

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUserId(String userId);

    @Query("select s from User s where s.userId= :userId and s.isDeleted= false")
    Optional<User> findActiveByUserId(@Param("userId") String userId);

    Boolean existsByUserId(String userId);

 //   Boolean existsByEmail(String email);

    List<User> findAll();

	Optional<User> findByUserIdAndPassword(String email, String password);
	
	
    
    
	
    
}
