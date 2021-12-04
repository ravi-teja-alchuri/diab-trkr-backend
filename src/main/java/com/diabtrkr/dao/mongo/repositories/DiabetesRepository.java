package com.diabtrkr.dao.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diabtrkr.models.Diabetes;

public interface DiabetesRepository extends MongoRepository<Diabetes, String> {

	List<Diabetes> findByUserIdAndDeleted(String userId, boolean deleted);

}
