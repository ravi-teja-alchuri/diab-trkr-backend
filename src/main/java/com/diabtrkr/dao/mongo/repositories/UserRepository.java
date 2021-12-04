package com.diabtrkr.dao.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diabtrkr.models.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByUsernameAndDeleted(String username, boolean deleted);

}
