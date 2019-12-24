package com.slintec.backend.service.impl;

import com.slintec.backend.data.Comment;
import com.slintec.backend.data.Department;
import com.slintec.backend.data.Institute;
import com.slintec.backend.data.Instrument;


import com.slintec.backend.repo.CommentRepository;
import com.slintec.backend.repo.DepartmentRepository;
import com.slintec.backend.repo.InstituteRepository;
import com.slintec.backend.repo.InstrumentRepository;
import com.slintec.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    InstrumentRepository instrumentRepository;

    @Autowired
    InstituteRepository instituteRepository;


    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CommentRepository commentRepository;





}
