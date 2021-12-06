package com.diabtrkr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diabtrkr.controllers.utils.PasswordUtils;
import com.diabtrkr.controllers.utils.TokenUtils;
import com.diabtrkr.dao.UserDao;
import com.diabtrkr.exceptions.GenericException;
import com.diabtrkr.models.User;
import com.diabtrkr.request.dtos.LoginDTO;
import com.diabtrkr.request.dtos.UserDTO;
import com.diabtrkr.response.dtos.LoginResponse;

@Service
public class UserService {

	@Autowired
	UserDao dao;

	@Autowired
	TokenUtils tu;

	@Autowired
	PasswordUtils passwordUtils;

	public User create(UserDTO dto) {
		dto.setUsername(dto.getUsername().toLowerCase());
		User existing = getByUsername(dto.getUsername());
		if (existing != null) {
			throw new GenericException("Username already exists, please use a different one");
		}

		String encryptedPassword = passwordUtils.getEncryptedPassword(dto.getPassword());
		if (encryptedPassword == null)
			throw new GenericException("Unable to encrypt the password");
		User model = new User();
		model.setUsername(dto.getUsername());
		model.setPassword(encryptedPassword);

		model.setFirstName(dto.getFirstName());
		model.setLastName(dto.getLastName());
		model.setGender(dto.getGender());
		model.setAge(dto.getAge());

		model = dao.add(model);
		model.setPassword(null);
		return model;
	}

	private User getByUsername(String username) {
		return dao.getByUsername(username);
	}

	public LoginResponse login(LoginDTO dto) {
		User user = dao.getByUsername(dto.getUsername());
		if (user == null) {
			throw new GenericException("Incorrect username or password");
		}
		boolean valid = passwordUtils.validatePassword(dto.getPassword(), user.getPassword());
		if (!valid) {
			throw new GenericException("Invalid username or password");
		}

		String token = tu.generateToken(user);
		user.setPassword(null);
		LoginResponse res = new LoginResponse();
		res.setUser(user);
		res.setToken(token);

		return res;
	}

	public void setFirstTime(boolean firstTime) {
		dao.setFirstTime(tu.getUserId(), firstTime);

	}

}
