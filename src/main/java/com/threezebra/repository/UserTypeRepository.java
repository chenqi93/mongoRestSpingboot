package com.threezebra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;

public interface UserTypeRepository extends MongoRepository<UserType, String> {
	UserType findById(long id);
   List<UserType> findByUnit(Unit unit);
     UserType findByName(String name);
}
