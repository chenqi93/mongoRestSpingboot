package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.User;

import java.util.Optional;

/**
 * @author vikas.sharma
 *
 */
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserName(String userName);

}
