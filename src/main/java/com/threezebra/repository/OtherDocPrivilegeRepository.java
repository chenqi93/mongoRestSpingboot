package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.OtherDocPrivilege;

public interface OtherDocPrivilegeRepository extends MongoRepository<OtherDocPrivilege, String> {

	OtherDocPrivilege findByName(String otherdocprev);

	OtherDocPrivilege findById(long otherdocprevId);

}
