package com.slintec.backend.controller;

import com.slintec.backend.data.Department;
import com.slintec.backend.data.Institute;
import com.slintec.backend.data.Instrument;
import com.slintec.backend.data.User;
import com.slintec.backend.exception.BadRequestException;
import com.slintec.backend.exception.ResourceNotFoundException;
import com.slintec.backend.payload.*;
import com.slintec.backend.repo.DepartmentRepository;
import com.slintec.backend.repo.InstituteRepository;
import com.slintec.backend.repo.InstrumentRepository;
import com.slintec.backend.repo.UserRepository;
import com.slintec.backend.security.CurrentUser;
import com.slintec.backend.security.UserPrincipal;
import com.slintec.backend.service.DepartmentService;
import com.slintec.backend.service.InstituteService;
import com.slintec.backend.service.InstrumentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InstrumentController {

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

    @Autowired
    InstrumentService instrumentService;

    @PostMapping("/add/instrument")
    @PreAuthorize("hasRole('ADMIN_LEVEL_1')")
    public InstrumentProfile addInstrument (@CurrentUser UserPrincipal currentUser, @RequestBody InstrumentRegisterRequest instrumentRegisterRequest)
    {
        User custodian=userRepository.findByUserNameOrEmailOrNicAndDeleted(instrumentRegisterRequest.getCustodianUsernameOrEmailOrNic(),instrumentRegisterRequest.getCustodianUsernameOrEmailOrNic(),instrumentRegisterRequest.getCustodianUsernameOrEmailOrNic(),false)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UsernameOrEmailOrNic", instrumentRegisterRequest.getCustodianUsernameOrEmailOrNic()));

        User cUser=userRepository.findByNic(currentUser.getNic()).orElse(null);

        Institute institute=instituteRepository.findByInstituteEmail(cUser.getInstitute()).orElse(null);

        if(custodian.getInstitute()==null){
        }else{
        if(!custodian.getInstitute().equals(institute.getInstituteEmail())){
            throw new BadRequestException("custodian is already assigned to another institute");
        }
        }

        Department department=departmentRepository.findByDepartmentName(cUser.getDepartment()).orElse(null);

        ModelMapper modelMapper=new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        Instrument instrument=new Instrument();
        modelMapper.map(instrumentRegisterRequest,instrument);

        return instrumentService.addInstrument(department,institute,custodian,instrumentRegisterRequest);
    }

    //admin function ADMIN
    @DeleteMapping("/delete/instrument")
    @PreAuthorize("hasRole('ADMIN_LEVEL_1')")
    public InstrumentProfile DeleteInstrument (@CurrentUser UserPrincipal currentUser, @RequestParam(value = "dev") Long ins)
    {
        User cUser=userRepository.findByNic(currentUser.getNic()).orElse(null);
        Instrument instrument=instrumentRepository.findById(ins)
                .orElseThrow(() -> new ResourceNotFoundException("Instrument", "instrumentId", ins));

        if(instrument.getDepartment().getDepartmentName()!=cUser.getDepartment()){
            throw new BadRequestException("level 1 admin can only delete institutes belongs to him");
        }


        Instrument insRet=instrumentService.deleteInstrument(instrument);
        ModelMapper modelMapper=new ModelMapper();


        InstrumentProfile ret=modelMapper.map(insRet, InstrumentProfile.class);
        return ret;
    }

    //get mappings
    @GetMapping("/current/instruments")
    @PreAuthorize("hasRole('ROLE_ADMIN_LEVEL_1') or hasRole('ROLE_ADMIN_LEVEL_2')")
    public List<InstrumentProfile> getCurrentInstrument(@CurrentUser UserPrincipal currentUser) {
        User cUser=userRepository.findById(currentUser.getId()).orElse(null);
        List<Instrument> instruments= instrumentRepository.findAllByCustodianMailAndDeleted(cUser.getEmail(),false);

        ModelMapper modelMapper=new ModelMapper();
        Type listType = new TypeToken<List<InstrumentProfile>>(){}.getType();
        List<InstrumentProfile> instrumentProfiles = modelMapper.map(instruments,listType);

        return instrumentProfiles;
    }

    @GetMapping("/instruments/all")
    public List<InstrumentProfile> getAllInstruments() {

        List<Instrument> instruments= instrumentRepository.findAllByDeleted(false);

        ModelMapper modelMapper=new ModelMapper();
        Type listType = new TypeToken<List<InstrumentProfile>>(){}.getType();
        List<InstrumentProfile> instrumentProfiles = modelMapper.map(instruments,listType);

        return instrumentProfiles;
    }


}


