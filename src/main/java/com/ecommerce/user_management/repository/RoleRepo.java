package com.ecommerce.user_management.repository;

import com.ecommerce.user_management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
Role findByName(String name);
}
