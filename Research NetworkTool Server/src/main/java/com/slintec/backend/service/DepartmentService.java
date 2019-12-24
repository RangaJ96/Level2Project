package com.slintec.backend.service;


import com.slintec.backend.data.Department;
import com.slintec.backend.data.Institute;
import com.slintec.backend.data.User;
import com.slintec.backend.payload.DepartmentProfile;

public interface DepartmentService {

    DepartmentProfile addDepartment(Department department, Institute institute, User depHead);

    Department deleteDepartment(Department department);
}
