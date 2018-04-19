package com.pughlab.trialmatchapi.repository;


import com.pughlab.trialmatchapi.domain.Trial;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for REST APIs for retrieving trial
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-19
 */

public interface TrialRepository extends MongoRepository<Trial, Integer> {

    Trial findById(String id);

    Trial findTrialByNctID(String nctId);

    @Override
    void delete(Trial trail);
}

