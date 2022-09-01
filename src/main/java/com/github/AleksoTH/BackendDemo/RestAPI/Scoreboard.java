package com.github.AleksoTH.BackendDemo.RestAPI;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.AleksoTH.BackendDemo.DataRepositories.ScoreboardRepository;
import com.github.AleksoTH.BackendDemo.DataRepositories.UserRepository;
import com.github.AleksoTH.BackendDemo.DataStructures.ScoreEntry;
import com.github.AleksoTH.BackendDemo.DataStructures.User;
import com.github.AleksoTH.BackendDemo.Utils.ResponseObject;
import com.google.gson.Gson;

@RestController
public class Scoreboard {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	ScoreboardRepository repo_scores;
	
	final static private Gson gson = new Gson();
	
	@GetMapping(path="/scoreboard", produces = "application/json")
    public List<ScoreEntry> getScores() 
    {
        return repo_scores.findAll();
    }
    
    @PostMapping(path="/{user_id}/new_score", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> new_score(@PathVariable Long user_id,@RequestParam Map<String, String> json) 
    {
    	//Perform session verification
    	SecurityContext securityContext = SecurityContextHolder.getContext();
    	Authentication authentication = securityContext.getAuthentication();
    	UserDetails user = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            user = principal instanceof UserDetails ? (UserDetails) principal : null;
            
        }
        if(user == null) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(gson.toJson(new ResponseObject("Not logged into the correct account",HttpStatus.BAD_REQUEST)));
        } 
        String username = user.getUsername();
    	Optional<User> copy = repo.findById(user_id);
    	if(copy.isEmpty() || !copy.get().getUsername().equals(username)) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(gson.toJson(new ResponseObject("Not logged into the correct account",HttpStatus.BAD_REQUEST)));
		}
    	long score = 0;
        try {
        	score = Long.parseLong(json.get("score"));
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(gson.toJson(new ResponseObject("Error: missing score",HttpStatus.BAD_REQUEST)));
        }
		ScoreEntry scoreE = new ScoreEntry();
		scoreE.setScore(score);
		User customer = copy.get();
		customer.getScores().add(scoreE);
		customer = repo.save(customer);
		return ResponseEntity.status(HttpStatus.OK).body(customer);

	}
    
}
