package com.slintec.backend.service.impl;

import com.slintec.backend.data.Institute;
import com.slintec.backend.data.Role;
import com.slintec.backend.data.RoleName;
import com.slintec.backend.data.User;
import com.slintec.backend.payload.InstituteProfile;
import com.slintec.backend.payload.InstituteRegisterRequest;
import com.slintec.backend.payload.UserSummery;
import com.slintec.backend.repo.InstituteRepository;
import com.slintec.backend.repo.RoleRepository;
import com.slintec.backend.repo.UserRepository;
import com.slintec.backend.service.InstituteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituteServiceImpl implements InstituteService {
    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public InstituteProfile addInstitute(User cUser, InstituteRegisterRequest instituteRegisterRequest) {
        Institute institute=new Institute();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(instituteRegisterRequest,institute);

        //institute
        institute.setState(0);
        institute.setInstituteHead(cUser);
        institute.setHeadEmail(cUser.getEmail());


        //user
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN_LEVEL_0)
                .orElse(null);

        if (userRole == null) {
            Role role = new Role();
            role.setName(RoleName.ROLE_ADMIN_LEVEL_0);
            userRole=roleRepository.saveAndFlush(role);

        }

        cUser.setRoles(userRole);
        cUser.setRole(RoleName.ROLE_ADMIN_LEVEL_0);
        cUser.setInstitute(institute.getInstituteEmail());
        User result = userRepository.save(cUser);


        //map
        InstituteProfile instituteProfile=new InstituteProfile();
        ModelMapper modelMapper1=new ModelMapper();
        modelMapper1.map(institute,instituteProfile);
        instituteProfile.setCreated(institute.getCreated());
        instituteProfile.setUpdated(institute.getUpdated());
        instituteProfile.setCreatedBy(cUser);
        instituteProfile.setUpdatedBy(cUser);
        instituteRepository.saveAndFlush(institute);


        return instituteProfile;
    }
}
