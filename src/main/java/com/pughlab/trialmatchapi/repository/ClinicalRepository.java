package com.pughlab.trialmatchapi.repository;


import com.pughlab.trialmatchapi.domain.Clinical;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for REST APIs for retrieving clinical information to trials
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-19
 */

public interface ClinicalRepository extends MongoRepository<Clinical, Integer> {

    Clinical findById(String id);

    @Override
    void delete(Clinical clinical);
}

