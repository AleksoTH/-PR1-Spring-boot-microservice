package com.github.AleksoTH.BackendDemo.DataRepositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.AleksoTH.BackendDemo.DataStructures.ScoreEntry;

@Repository
public interface ScoreboardRepository extends CrudRepository<ScoreEntry, Integer>{
	List<ScoreEntry> findAll();
}
