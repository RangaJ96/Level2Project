package com.slintec.backend.repo;

import com.slintec.backend.data.Institute;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {


 Optional<Institute> findByInstituteEmail(String instituteEmail);
}
