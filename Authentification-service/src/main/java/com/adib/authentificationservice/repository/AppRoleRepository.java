package com.adib.authentificationservice.repository;

import com.adib.authentificationservice.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
    }
    
