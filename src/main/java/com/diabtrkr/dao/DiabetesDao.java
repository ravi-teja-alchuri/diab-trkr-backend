package com.diabtrkr.dao;

import java.util.List;

import com.diabtrkr.models.Diabetes;

public interface DiabetesDao {

	Diabetes save(Diabetes model);

	List<Diabetes> getByUserId(String userId);

}
