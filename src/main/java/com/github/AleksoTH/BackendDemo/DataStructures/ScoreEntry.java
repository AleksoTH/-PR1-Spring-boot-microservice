package com.github.AleksoTH.BackendDemo.DataStructures;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "scores")
@Entity
public class ScoreEntry {


	@Id
    @GeneratedValue
    private long id;
	
	private long score;
	
	@ManyToOne
	private User user;
	
   

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public long getScore() {
		return score;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setScore(long score) {
		this.score = score;
	}
	
	
	

}
