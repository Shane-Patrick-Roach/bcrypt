package com.bcrypt.bcrypt.controller;


import com.bcrypt.bcrypt.BcryptApplication;
import com.bcrypt.bcrypt.model.SiteUser;
import com.bcrypt.bcrypt.repository.SiteUserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    SiteUserRepository siteUserRepository;
    @GetMapping("/")
    public String getLoginPage() {
        return "login.html";
    }


    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        SiteUser newUser = new SiteUser(username,hashedPassword);
        siteUserRepository.save(newUser);

        return new RedirectView("/");
    }

    @PostMapping("/login")
    public RedirectView loginSubmit(String username, String password){
        SiteUser loggingInUser = siteUserRepository.findByUsername(username);

        if (loggingInUser == null){
            return new RedirectView("/");
        }

        Boolean isPasswordCorrect = BCrypt.checkpw(password, loggingInUser.getPassword());

        if (!isPasswordCorrect){
            return new RedirectView("/");
        }  else {
            return new RedirectView("/secretPage/" + username);
        }
    }





}
