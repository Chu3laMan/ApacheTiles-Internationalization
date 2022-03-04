package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Role;
import com.example.demo.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
    Optional<Role> findByName(RoleName name);
    
    Optional<Role> findById(Long id);
    
    List<Role> findAll();
    
    
}
