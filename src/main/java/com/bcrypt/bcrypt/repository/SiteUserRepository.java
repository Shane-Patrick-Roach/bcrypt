package com.bcrypt.bcrypt.repository;

import com.bcrypt.bcrypt.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long>{
    SiteUser findByUsername(String username);
}
