package com.speedroller.app_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speedroller.app_v1.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameString(String nameString);
}

