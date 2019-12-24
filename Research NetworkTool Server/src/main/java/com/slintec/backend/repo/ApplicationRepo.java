package com.slintec.backend.repo;

import com.slintec.backend.data.Application;
import com.slintec.backend.data.Limitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
}
