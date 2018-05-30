package com.pughlab.trialmatchapi.repository;

import com.pughlab.trialmatchapi.domain.TrialMatch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

/**
 * Interface for REST APIs for retrieving matched patient clinical and genomic information to trials
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-09
 */

public interface TrialMatchRepository extends MongoRepository<TrialMatch, Integer> {

    TrialMatch findById(String id);
    List<TrialMatch> findDistinctByHugoSymbol(String hugoSymbol);

    List<TrialMatch> findTrialMatchByHugoSymbol(String hugoSymbol);

    List<TrialMatch> findTrialMatchByHugoSymbolExists(String[] hugoSymbols);

    List<TrialMatch> findDistinctByNctIDAndAndHugoSymbol(String nctId, String hugoSymbol);

    List<TrialMatch> findDistinctByGenomicID(String genomicId);

    List<TrialMatch> findDistinctByProteinChangeAndSampleID(String proteinChange, String sampleId);

    List<TrialMatch> findDistinctBySampleID(String sampleId);

    //Supports native JSON query string
    @Query("{sample_id:'?0'}")
    List<TrialMatch> findTrialMatchBySampleID(String sample_id);

    @Query("{sample_id: { $regex: ?0 } })")
    List<TrialMatch> findCustomByRegExSSampleID(String sampleID);

    @Override
    void delete(TrialMatch match);
}