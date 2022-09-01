package com.github.AleksoTH.BackendDemo.RestAPI;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.AleksoTH.BackendDemo.DataRepositories.UserRepository;
import com.github.AleksoTH.BackendDemo.DataStructures.ScoreEntry;
import com.github.AleksoTH.BackendDemo.DataStructures.User;

@RestController
public class Login {
	
	@Autowired
	UserRepository repo;
	
	@GetMapping(path="/login", produces = "application/json")
    public User login(@AuthenticationPrincipal UserDetails currentUser) 
    {
		Optional<User> checkUser = repo.findUserByUsername(currentUser.getUsername());
        if(checkUser.isEmpty()) {
        	User new_user = new User();
        	new_user.setUsername(currentUser.getUsername());
        	return repo.save(new_user);
        }
		return checkUser.get();
    }

}
