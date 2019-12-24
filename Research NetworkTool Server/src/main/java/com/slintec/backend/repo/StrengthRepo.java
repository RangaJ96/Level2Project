package com.slintec.backend.repo;

import com.slintec.backend.data.Role;
import com.slintec.backend.data.Strength;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrengthRepo extends JpaRepository<Strength, Long> {
}
