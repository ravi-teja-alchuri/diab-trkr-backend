package com.diabtrkr.dao;

import com.diabtrkr.models.User;

public interface UserDao {

	User add(User model);

	User getByUsername(String username);

	void setFirstTime(String id, boolean firstTime);

}
