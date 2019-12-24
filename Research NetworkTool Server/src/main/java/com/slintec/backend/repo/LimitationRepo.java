package com.slintec.backend.repo;

import com.slintec.backend.data.Limitation;
import com.slintec.backend.data.Strength;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitationRepo extends JpaRepository<Limitation, Long> {
}
