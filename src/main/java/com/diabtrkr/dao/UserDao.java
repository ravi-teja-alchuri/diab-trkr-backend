package com.diabtrkr.dao;

import com.diabtrkr.models.User;

public interface UserDao {

	User add(User model);

	User getByUsernameAndPassword(String username, String password);

	User getByUsername(String username);

}
