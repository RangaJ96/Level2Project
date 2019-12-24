package com.slintec.backend.repo;

import com.slintec.backend.data.Category;
import com.slintec.backend.data.TechnicalSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
