package com.rana.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rana.springcloud.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
