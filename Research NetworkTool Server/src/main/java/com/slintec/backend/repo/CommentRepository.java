package com.slintec.backend.repo;

import com.slintec.backend.data.Comment;
import com.slintec.backend.data.Department;
import com.slintec.backend.data.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
