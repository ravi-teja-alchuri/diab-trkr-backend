package com.diabtrkr.dao.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diabtrkr.models.Therapy;

public interface TherapyRepository extends MongoRepository<Therapy, String> {

	Therapy findByUserIdAndDeleted(String userId, boolean deleted);

}
