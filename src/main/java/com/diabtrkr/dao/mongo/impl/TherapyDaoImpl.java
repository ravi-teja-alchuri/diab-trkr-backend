package com.diabtrkr.dao.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diabtrkr.dao.TherapyDao;
import com.diabtrkr.dao.mongo.repositories.TherapyRepository;
import com.diabtrkr.models.Therapy;

@Repository
public class TherapyDaoImpl implements TherapyDao {

	@Autowired
	TherapyRepository repo;

	@Override
	public Therapy save(Therapy therapy) {
		return repo.save(therapy);
	}

	@Override
	public Therapy getByUserId(String userId) {
		return repo.findByUserIdAndDeleted(userId, false);
	}
}
