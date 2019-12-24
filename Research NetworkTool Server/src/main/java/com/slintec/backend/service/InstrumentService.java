package com.slintec.backend.service;


import com.slintec.backend.data.Department;
import com.slintec.backend.data.Institute;
import com.slintec.backend.data.Instrument;
import com.slintec.backend.data.User;
import com.slintec.backend.payload.DepartmentProfile;
import com.slintec.backend.payload.InstrumentProfile;
import com.slintec.backend.payload.InstrumentRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InstrumentService {


    InstrumentProfile addInstrument(Department department, Institute institute, User custodian, InstrumentRegisterRequest instrumentRegisterRequest);

    Instrument deleteInstrument(Instrument instrument);
}
