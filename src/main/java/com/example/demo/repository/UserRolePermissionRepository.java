package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.UserRolePermission;

@Repository
@Transactional
public interface UserRolePermissionRepository extends JpaRepository<UserRolePermission,Long> {

    List<UserRolePermission> findByUserId(Long id);

    
    @Query(value = "update t_user_permission set is_deleted=true where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    
    @Query(value = "update t_user_permission set is_deleted=true where user_id = :id", nativeQuery = true)
    void deleteByUserId(@Param("id") Long id);
    
}
