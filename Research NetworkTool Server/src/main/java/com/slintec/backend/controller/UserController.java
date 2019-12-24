package com.slintec.backend.controller;

import com.slintec.backend.data.User;
import com.slintec.backend.exception.ResourceNotFoundException;
import com.slintec.backend.payload.UserProfile;
import com.slintec.backend.repo.UserRepository;
import com.slintec.backend.security.CurrentUser;
import com.slintec.backend.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/current/user")
    @PreAuthorize("hasRole('USER')")
    public UserProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user=userRepository.findById(currentUser.getId()).orElse(null);
        UserProfile userProfile=new UserProfile();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(user,userProfile);
        userProfile.setJoinedAt(user.getCreated());
        userProfile.setLastLoggedIn(user.getUpdated());
        userProfile.setRole(user.getRole());
        return userProfile;
    }


//should move to admin controller
    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        return null;
    }

    @DeleteMapping("delete/profile")
    public UserProfile deleteProfile(@CurrentUser UserPrincipal currentUser){
        User cUser=userRepository.findByNic(currentUser.getNic()).orElse(null);
        cUser.setDeleted(true);
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(cUser,UserProfile.class);
    }



}
