package com.slintec.backend.service;

import com.slintec.backend.data.Institute;
import com.slintec.backend.data.User;
import com.slintec.backend.payload.InstituteProfile;
import com.slintec.backend.payload.InstituteRegisterRequest;

import java.util.List;

public interface InstituteService {

    InstituteProfile addInstitute(User cUser, InstituteRegisterRequest instituteRegisterRequest);
}
