package com.diabtrkr.dao.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diabtrkr.dao.UserDao;
import com.diabtrkr.dao.mongo.repositories.UserRepository;
import com.diabtrkr.models.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository repo;

	@Override
	public User add(User model) {
		return repo.save(model);
	}

	@Override
	public User getByUsername(String username) {
		return repo.findByUsernameAndDeleted(username, false);
	}

}
