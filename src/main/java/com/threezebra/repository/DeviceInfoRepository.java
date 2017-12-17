package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.DeviceInfo;

public interface DeviceInfoRepository extends MongoRepository<DeviceInfo, String>{
   DeviceInfo findById(long id);

}
