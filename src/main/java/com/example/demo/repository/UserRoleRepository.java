package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
 
    Optional<UserRole> findById(Long id);
    

    @Query("select r from UserRole r where r.user.id = :userId ")
    List<UserRole> findByUserId(@Param("userId") Long userId);


    Optional<UserRole> findByUserIdAndRoleNameIgnoreCase(@Param("id") Long id,@Param("name")  String name);

    
}