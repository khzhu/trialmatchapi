package com.pughlab.trialmatchapi.web;


import com.pughlab.trialmatchapi.domain.TrialMatch;

public interface TrialMatchService {
    Iterable<TrialMatch> listAllTrialMatches();

    TrialMatch getTrialMatchById(String id);

    Iterable<TrialMatch> getTrialMatchByHugoSymbol(String hugoSymbol);

    TrialMatch saveTrialMatch(TrialMatch trialMatch);

    void delete(String id);
}
