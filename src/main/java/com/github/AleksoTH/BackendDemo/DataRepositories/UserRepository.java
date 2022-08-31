package com.github.AleksoTH.BackendDemo.DataRepositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.AleksoTH.BackendDemo.DataStructures.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	Optional<User> findById(Long id);
}
