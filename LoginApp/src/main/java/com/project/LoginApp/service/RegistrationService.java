package com.project.LoginApp.service;

import com.project.LoginApp.model.AppUser;
import com.project.LoginApp.model.AppUserRole;
import com.project.LoginApp.model.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidationService emailValidationService;

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidationService.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                ));
    }
}
