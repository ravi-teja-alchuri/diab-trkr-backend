package com.diabtrkr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diabtrkr.controllers.utils.TokenUtils;
import com.diabtrkr.dao.TherapyDao;
import com.diabtrkr.models.Therapy;
import com.diabtrkr.request.dtos.TherapyDTO;

@Service
public class TherapyService {

	@Autowired
	TherapyDao dao;

	@Autowired
	UserService userService;

	@Autowired
	TokenUtils tu;

	public Therapy create(TherapyDTO dto) {
		Therapy model = new Therapy();
		model.setUserId(tu.getUserId());

		model.setTherapy(dto.getTherapy());
		model.setType(dto.getType());
		model.setOnPills(dto.getOnPills());

		userService.setFirstTime(false);
		return dao.save(model);
	}

	public Therapy get() {
		return dao.getByUserId(tu.getUserId());
	}

}
