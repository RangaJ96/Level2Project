package com.slintec.backend.controller;

import com.slintec.backend.data.Department;
import com.slintec.backend.data.Institute;
import com.slintec.backend.data.Instrument;
import com.slintec.backend.data.User;
import com.slintec.backend.exception.BadRequestException;
import com.slintec.backend.exception.ResourceNotFoundException;
import com.slintec.backend.payload.DepartmentProfile;
import com.slintec.backend.payload.DepartmentRegisterRequest;
import com.slintec.backend.payload.InstituteProfile;
import com.slintec.backend.payload.InstituteRegisterRequest;
import com.slintec.backend.repo.DepartmentRepository;
import com.slintec.backend.repo.InstituteRepository;
import com.slintec.backend.repo.InstrumentRepository;
import com.slintec.backend.repo.UserRepository;
import com.slintec.backend.security.CurrentUser;
import com.slintec.backend.security.UserPrincipal;
import com.slintec.backend.service.DepartmentService;
import com.slintec.backend.service.InstituteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    InstrumentRepository instrumentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    InstituteService instituteService;

    @Autowired
    DepartmentService departmentService;


    @PostMapping("/add/department")
    @PreAuthorize("hasRole('ADMIN_LEVEL_0')")
    public DepartmentProfile addDepartment (@CurrentUser UserPrincipal currentUser,@RequestBody DepartmentRegisterRequest departmentRegisterRequest)
    {
        User depHead=userRepository.findByUserNameOrEmailOrNicAndDeleted(departmentRegisterRequest.getHeadUsernameOrEmailOrNic(),departmentRegisterRequest.getHeadUsernameOrEmailOrNic(),departmentRegisterRequest.getHeadUsernameOrEmailOrNic(),false)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UsernameOrEmailOrNic", departmentRegisterRequest.getHeadUsernameOrEmailOrNic()));

        if(depHead.getInstitute()!=null){
        throw new BadRequestException("user can be given only one role");
         }
        User cUser=userRepository.findByNic(currentUser.getNic()).orElse(null);

        Institute institute=instituteRepository.findByInstituteEmail(cUser.getInstitute()).orElse(null);

        ModelMapper modelMapper=new ModelMapper();
        Department department=modelMapper.map(departmentRegisterRequest,Department.class);

        return departmentService.addDepartment(department,institute,depHead);
    }

    //admin function ADMIN
    @DeleteMapping("/delete/department")
    @PreAuthorize("hasRole('ADMADMIN_LEVEL_0')")
    public DepartmentProfile Delete (@CurrentUser UserPrincipal currentUser, @RequestParam(value = "dep") Long dep)
    {
        User cUser=userRepository.findByNic(currentUser.getNic()).orElse(null);
        Department department=departmentRepository.findById(dep)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "departmentId", dep));

        if(department.getInstitute().getHeadEmail()!=currentUser.getEmail()){
            throw new BadRequestException("level 0 admin can only delete institutes belongs to him");
        }



        Department depRet=departmentService.deleteDepartment(department);
        ModelMapper modelMapper=new ModelMapper();

        DepartmentProfile ret=modelMapper.map(depRet,DepartmentProfile.class);
        return ret;
    }

    //get mappings
    @GetMapping("/current/department")
    @PreAuthorize("hasRole('ROLE_ADMIN_LEVEL_1') or hasRole('ROLE_ADMIN_LEVEL_2')")
    public DepartmentProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User cUser=userRepository.findById(currentUser.getId()).orElse(null);
        Department department= departmentRepository.findByDepartmentName(cUser.getDepartment()).orElse(null);

        ModelMapper modelMapper=new ModelMapper();
        DepartmentProfile departmentProfile=modelMapper.map(department,DepartmentProfile.class);

        return departmentProfile;
    }
}
