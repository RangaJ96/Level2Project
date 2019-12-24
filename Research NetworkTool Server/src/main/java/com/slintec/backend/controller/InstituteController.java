package com.slintec.backend.controller;

import com.slintec.backend.data.Institute;
import com.slintec.backend.data.User;
import com.slintec.backend.exception.BadRequestException;
import com.slintec.backend.exception.ResourceNotFoundException;
import com.slintec.backend.payload.InstituteProfile;
import com.slintec.backend.payload.InstituteRegisterRequest;
import com.slintec.backend.payload.UserProfile;
import com.slintec.backend.repo.DepartmentRepository;
import com.slintec.backend.repo.InstituteRepository;
import com.slintec.backend.repo.InstrumentRepository;
import com.slintec.backend.repo.UserRepository;
import com.slintec.backend.security.CurrentUser;
import com.slintec.backend.security.UserPrincipal;
import com.slintec.backend.service.InstituteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InstituteController {
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


    @PostMapping("/add/institute")
    @PreAuthorize("hasRole('ROLE_USER')")
    public InstituteProfile addInstitute (@CurrentUser UserPrincipal currentUser, @RequestBody InstituteRegisterRequest instituteRegisterRequest)
    {
        User cUser=userRepository.findByNic(currentUser.getNic()).orElse(null);
        if(cUser.getInstitute()!=null){
            throw new BadRequestException("user can be given only one role");
        }



    return instituteService.addInstitute(cUser,instituteRegisterRequest);
    }

//admin function ADMIN
    @PutMapping("/conform/institute")
    @PreAuthorize("hasRole('ADMIN')")
    public InstituteProfile conform (@CurrentUser UserPrincipal currentUser, @RequestParam(value = "in") Long ins)
    {
        User cUser=userRepository.findByNic(currentUser.getNic()).orElse(null);
        Institute institute=instituteRepository.findById(ins)
                .orElseThrow(() -> new ResourceNotFoundException("Institute", "instituteId", ins));

        institute.setState(1);
        Institute instituteReturn=instituteRepository.saveAndFlush(institute);
        ModelMapper modelMapper=new ModelMapper();

        InstituteProfile ret=modelMapper.map(instituteReturn,InstituteProfile.class);
        return ret;
    }


    //getmappings
    @GetMapping("/current/institute")
    @PreAuthorize("hasRole('ROLE_ADMIN_LEVEL_0') or hasRole('ROLE_ADMIN_LEVEL_1') or hasRole('ROLE_ADMIN_LEVEL_2')")
    public InstituteProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User cUser=userRepository.findById(currentUser.getId()).orElse(null);
       Institute institute= instituteRepository.findByInstituteEmail(cUser.getInstitute()).orElse(null);

        ModelMapper modelMapper=new ModelMapper();
        InstituteProfile instituteProfile=modelMapper.map(institute,InstituteProfile.class);
        instituteProfile.setCreated(institute.getCreated());
        instituteProfile.setUpdated(institute.getUpdated());
        User createdBy=userRepository.findById(institute.getCreatedBy()).orElse(null);
        instituteProfile.setCreatedBy(createdBy);
        User updatedBy=userRepository.findById(institute.getUpdatedBy()).orElse(null);
        instituteProfile.setUpdatedBy(updatedBy);
        return instituteProfile;
    }

}
