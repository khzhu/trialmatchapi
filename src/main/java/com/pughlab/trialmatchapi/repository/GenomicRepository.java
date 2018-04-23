package com.pughlab.trialmatchapi.repository;




import com.pughlab.trialmatchapi.domain.Genomic;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for REST APIs for retrieving trial
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-21
 */

public interface GenomicRepository extends MongoRepository<Genomic, Integer> {

    Genomic findById(String id);

    Genomic findTrialByHugoSymbolAndProteinChange(String hugoSymbol, String proteinChange);

    @Override
    void delete(Genomic genomic);
}