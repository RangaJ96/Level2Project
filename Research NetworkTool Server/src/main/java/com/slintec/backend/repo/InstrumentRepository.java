package com.slintec.backend.repo;

import com.slintec.backend.data.Institute;
import com.slintec.backend.data.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {


    List<Instrument> findAllByCustodianMailAndDeleted(String email,Boolean isDeleted);
    List<Instrument> findAllByDeleted(Boolean isDeleted);
}
