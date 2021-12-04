package com.diabtrkr.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diabtrkr.controllers.utils.TokenUtils;
import com.diabtrkr.dao.DiabetesDao;
import com.diabtrkr.models.Diabetes;
import com.diabtrkr.request.dtos.DiabetesDTO;

@Service
public class DiabetesService {

	@Autowired
	DiabetesDao dao;

	@Autowired
	TokenUtils tu;

	public Diabetes create(DiabetesDTO dto) {
		Diabetes model = new Diabetes();
		model.setUserId(tu.getUserId());

		model.setTime(dto.getTime());
		if (dto.getTime() == null) {
			model.setTime(new Date());
		}
		model.setBloodSugarLevel(dto.getBloodSugarLevel());
		model.setCarbs(dto.getCarbs());
		model.setInsulin(dto.getInsulin());
		model.setMealType(dto.getMealType());

		return dao.save(model);
	}

	public List<Diabetes> get() {
		return dao.getByUserId(tu.getUserId());
	}

}
