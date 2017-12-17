package com.threezebra.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.TimeEntry;

import java.util.Collection;

/**
 * @author vikas.sharma
 *
 */
public interface TimeEntryRepository extends MongoRepository<TimeEntry, String> {

    Collection<TimeEntry> findByUserName(String userName);

    Collection<TimeEntry> findByUserNameAndDateBetween(
            String userName, Integer dateFrom, Integer dateTo);

    void deleteByUserName(String userName);
}
