package com.pughlab.trialmatchapi.web;


import com.pughlab.trialmatchapi.domain.TrialMatch;
import java.util.List;

public interface TrialMatchService {
    Iterable<TrialMatch> listAllTrialMatches();

    TrialMatch getTrialMatchById(String id);

    List<TrialMatch> getTrialMatchByHugoSymbol(String hugoSymbol);

    List<TrialMatch> findTrialMatchByHugoSymbolExists(String[] hugoSymbols);

    List<TrialMatch> getTrialMatchByNctIdAndHugoSymbol(String nctId, String hugoSymbol);

    List<TrialMatch> getTrialMatchBySampleId(String sampleId);

    List<TrialMatch> findDistinctByGenomicID(String genomicId);

    List<TrialMatch> findDistinctByGeneAndProteinChangeAndSampleID(String gene, String proteinChange, String sampleId);

    TrialMatch saveTrialMatch(TrialMatch trialMatch);

    void delete(String id);
}