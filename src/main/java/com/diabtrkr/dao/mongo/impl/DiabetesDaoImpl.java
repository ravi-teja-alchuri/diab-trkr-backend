package com.diabtrkr.dao.mongo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diabtrkr.dao.DiabetesDao;
import com.diabtrkr.dao.mongo.repositories.DiabetesRepository;
import com.diabtrkr.models.Diabetes;

@Repository
public class DiabetesDaoImpl implements DiabetesDao {

	@Autowired
	DiabetesRepository repo;

	@Override
	public Diabetes save(Diabetes model) {
		return repo.save(model);
	}

	@Override
	public List<Diabetes> getByUserId(String userId) {
		return repo.findByUserIdAndDeleted(userId, false);
	}

}
