package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.SharedDocPrivilege;

public interface SharedDocPrivilegeRepository extends MongoRepository<SharedDocPrivilege, String>{
	
	SharedDocPrivilege findByName(String shareDocPrivilege);

	SharedDocPrivilege findById(long shareDocPrivilege);

}
