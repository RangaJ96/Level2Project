package com.slintec.backend.service.impl;

import com.slintec.backend.data.*;

import com.slintec.backend.payload.DepartmentProfile;
import com.slintec.backend.payload.InstituteProfile;
import com.slintec.backend.payload.InstrumentProfile;
import com.slintec.backend.payload.InstrumentRegisterRequest;
import com.slintec.backend.repo.*;
import com.slintec.backend.service.InstrumentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    InstrumentRepository instrumentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    StrengthRepo strengthRepo;

    @Autowired
    LimitationRepo limitationRepo;

    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    TechnicalSpecificationRepo technicalSpecificationRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public InstrumentProfile addInstrument(Department department, Institute institute, User custodian, InstrumentRegisterRequest instrumentRegisterRequest) {

        ModelMapper modelMapper1=new ModelMapper();

        modelMapper1.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        Instrument instrument=new Instrument();
        modelMapper1.map(instrumentRegisterRequest,instrument);

        //mapping the lists
        instrumentRegisterRequest.getStrengthsList().forEach((strength) -> {
            instrument.setStrengths(strengthRepo.save(new Strength(strength)));
        });

        instrumentRegisterRequest.getLimitationsList().forEach((limitation) -> {
            instrument.setLimitations(limitationRepo.save(new Limitation(limitation)));
        });

        instrumentRegisterRequest.getApplicationsList().forEach((application) -> {
            instrument.setApplications(applicationRepo.save(new Application(application)));
        });

        instrumentRegisterRequest.getTechnicalSpecificationsList().forEach((tecchnicalSpecification) -> {
            instrument.setTechnicalSpecifications(technicalSpecificationRepo.save(new TechnicalSpecification(tecchnicalSpecification)));
        });


        instrumentRegisterRequest.getCategoriesSet().forEach((category) -> {
            instrument.setCategories(categoryRepo.save(new Category(category)));
        });

        instrument.setDepartment(department);
        instrument.setInstitute(institute);
        instrument.setCustodian(custodian);
        instrument.setCustodianMail(custodian.getEmail());



     instrumentRepository.save(instrument);

        //depCustodianProf
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN_LEVEL_2)
                .orElse(null);

        if (userRole == null) {
            Role role = new Role();
            role.setName(RoleName.ROLE_ADMIN_LEVEL_2);
            userRole=roleRepository.saveAndFlush(role);

        }

        custodian.setRoles(userRole);
        custodian.setRole(RoleName.ROLE_ADMIN_LEVEL_1);
        custodian.setInstitute(institute.getInstituteEmail());
        custodian.setDepartment(department.getDepartmentName());
      //  custodian.setInstrument(instrument.getInstumentId());
        User result = userRepository.save(custodian);


        ModelMapper modelMapper=new ModelMapper();
        InstrumentProfile instrumentProfile=modelMapper.map(instrument,InstrumentProfile.class);
        return instrumentProfile;
    }

    @Override
    public Instrument deleteInstrument(Instrument instrument) {
        instrument.setState(-1);
        User custodian=instrument.getCustodian();
        deleteCustodian(custodian);

        return instrumentRepository.saveAndFlush(instrument);
    }

    public void deleteCustodian(User custodian){

        Role role=roleRepository.findByName(RoleName.ROLE_ADMIN_LEVEL_2).orElse(null);
        custodian.dropRoles(role);
        custodian.setInstitute(null);
        custodian.setDepartment(null);
      //  custodian.setInstrument((long) 0);
        userRepository.saveAndFlush(custodian);
    }
}
