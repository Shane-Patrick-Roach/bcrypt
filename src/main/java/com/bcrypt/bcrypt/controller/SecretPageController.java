package com.bcrypt.bcrypt.controller;


import com.bcrypt.bcrypt.model.Posts;
import com.bcrypt.bcrypt.model.SiteUser;
import com.bcrypt.bcrypt.repository.PostsRepository;
import com.bcrypt.bcrypt.repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SecretPageController {

    @Autowired
    SiteUserRepository siteUserRepository;
    @Autowired
    PostsRepository postsRepository;


    @GetMapping("/secretPage/{username}")
    public String getSecretPage(@PathVariable String username, Model m){
            SiteUser siteUserToView = siteUserRepository.findByUsername(username);
            m.addAttribute("username", username.toUpperCase());
            m.addAttribute("siteUser",siteUserToView);
            m.addAttribute("posts", siteUserToView.getPostsOfThisUser());
            return "secretPage.html";
    }

    @PostMapping("/add-post")
    public RedirectView addPost(long siteUserId, String text){
        SiteUser postsOfUser = siteUserRepository.getById(siteUserId);
        Posts postsToAdd = new Posts(text);
        postsToAdd.setPostsOfUser(postsOfUser);
        postsRepository.save(postsToAdd);
        String username = postsOfUser.getUsername();

        return new RedirectView("/secretPage/" + username);
    }

}
