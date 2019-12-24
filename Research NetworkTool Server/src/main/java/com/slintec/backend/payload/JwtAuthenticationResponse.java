package com.slintec.backend.payload;

import com.slintec.backend.data.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User user;

    public JwtAuthenticationResponse(String accessToken, User u) {
        this.accessToken = accessToken;
        this.user = u;
    }
}
