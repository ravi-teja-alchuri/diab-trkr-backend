package com.diabtrkr.dao.mongo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.diabtrkr.dao.UserDao;
import com.diabtrkr.dao.mongo.repositories.UserRepository;
import com.diabtrkr.models.User;
import com.mongodb.client.result.UpdateResult;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository repo;

	@Autowired
	MongoTemplate template;

	private static final Logger logger = LogManager.getLogger();

	@Override
	public User add(User model) {
		return repo.save(model);
	}

	@Override
	public User getByUsername(String username) {
		return repo.findByUsernameAndDeleted(username, false);
	}

	@Override
	public void setFirstTime(String id, boolean firstTime) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Update update = new Update();
		update.set("firstTime", firstTime);
		UpdateResult ur = template.updateFirst(query, update, User.class);
		if (ur.getModifiedCount() != 1)
			logger.error("Modified documents count: {}", ur.getModifiedCount());

	}

}
