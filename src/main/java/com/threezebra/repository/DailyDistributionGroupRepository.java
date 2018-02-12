/**
 * 
 */
package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.DailyDistributionGroup;

/**
 * @author vikas.sharma
 *
 */
public interface DailyDistributionGroupRepository extends MongoRepository<DailyDistributionGroup, String> {
    DailyDistributionGroup findByName(String name);

}
