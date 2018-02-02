package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.CallSheet;

public interface CallSheetRepository  extends MongoRepository<CallSheet, String>{

}
