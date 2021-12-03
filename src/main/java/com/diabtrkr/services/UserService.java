package com.diabtrkr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diabtrkr.controllers.utils.TokenUtils;
import com.diabtrkr.dao.UserDao;
import com.diabtrkr.exceptions.GenericException;
import com.diabtrkr.models.User;
import com.diabtrkr.request.dtos.LoginDTO;
import com.diabtrkr.response.dtos.LoginResponse;

@Service
public class UserService {

	@Autowired
	UserDao dao;

	@Autowired
	TokenUtils tu;

	public User create(User model) {
		User existing = getByUsername(model.getUsername());
		if (existing != null) {
			throw new GenericException("Username already exists, please use a different one");
		}

		return dao.add(model);
	}

	private User getByUsername(String username) {
		return dao.getByUsername(username);
	}

	public LoginResponse login(LoginDTO dto) {
		User user = dao.getByUsernameAndPassword(dto.getUsername(), dto.getPassword());
		if (user == null) {
			throw new GenericException("Incorrect username or password");
		}

		String token = tu.generateToken(user);

		LoginResponse res = new LoginResponse();
		res.setFirstTime(user.isFirstTime());
		res.setToken(token);

		return res;
	}

}
