package com.slintec.backend.service.impl;


import com.slintec.backend.data.*;
import com.slintec.backend.payload.DepartmentProfile;
import com.slintec.backend.payload.UserSummery;
import com.slintec.backend.repo.DepartmentRepository;
import com.slintec.backend.repo.InstituteRepository;
import com.slintec.backend.repo.RoleRepository;
import com.slintec.backend.repo.UserRepository;
import com.slintec.backend.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public DepartmentProfile addDepartment(Department department, Institute institute, User depHead) {
        department.setHeadEmail(depHead.getEmail());
        department.setInstitute(institute);
        department.setDepartmentHead(depHead);
        department.setState(1);
        departmentRepository.saveAndFlush(department);

        //depHeadProf
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN_LEVEL_1)
                .orElse(null);

        if (userRole == null) {
            Role role = new Role();
            role.setName(RoleName.ROLE_ADMIN_LEVEL_1);
            userRole=roleRepository.saveAndFlush(role);

        }

        depHead.setRoles(userRole);
        depHead.setRole(RoleName.ROLE_ADMIN_LEVEL_1);
        depHead.setInstitute(institute.getInstituteEmail());
        depHead.setDepartment(department.getDepartmentName());
        User result = userRepository.save(depHead);


        ModelMapper modelMapper=new ModelMapper();

        DepartmentProfile departmentProfile=modelMapper.map(department,DepartmentProfile.class);

        return departmentProfile;
    }

    @Override
    public Department deleteDepartment(Department department) {
        department.setState(-1);
        User headOfDept=department.getDepartmentHead();

        Role role=roleRepository.findByName(RoleName.ROLE_ADMIN_LEVEL_1).orElse(null);
        headOfDept.dropRoles(role);
        headOfDept.setInstitute(null);
        headOfDept.setDepartment(null);
        userRepository.saveAndFlush(headOfDept);

        return departmentRepository.saveAndFlush(department);
    }
}
