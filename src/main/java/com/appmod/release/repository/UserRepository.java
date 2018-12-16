package com.appmod.release.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.appmod.release.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	 User findByEmail(String email);
}
