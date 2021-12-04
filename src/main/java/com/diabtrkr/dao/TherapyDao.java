package com.diabtrkr.dao;

import com.diabtrkr.models.Therapy;

public interface TherapyDao {

	Therapy save(Therapy therapy);

	Therapy getByUserId(String userId);

}
