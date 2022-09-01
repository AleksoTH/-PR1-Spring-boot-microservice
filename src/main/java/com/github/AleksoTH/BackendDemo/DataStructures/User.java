package com.github.AleksoTH.BackendDemo.DataStructures;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "users")
@Entity
public class User {
	    @Id
	    @GeneratedValue
	    private long id;
	    
	    private String username;
	    
	    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<ScoreEntry> scores = new ArrayList<ScoreEntry>();

		public long getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public void setId(long id) {
			this.id = id;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public List<ScoreEntry> getScores() {
			return scores;
		}

		public void setScores(List<ScoreEntry> scores) {
			this.scores = scores;
		}
}
