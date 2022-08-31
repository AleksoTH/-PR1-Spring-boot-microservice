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
    private int id;
	
	private long score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public long getScore() {
		return score;
	}

	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
