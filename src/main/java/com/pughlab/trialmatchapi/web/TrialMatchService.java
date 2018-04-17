package com.pughlab.trialmatchapi.web;


import com.pughlab.trialmatchapi.domain.TrialMatch;
import java.util.List;

public interface TrialMatchService {
    Iterable<TrialMatch> listAllTrialMatches();

    TrialMatch getTrialMatchById(String id);

    List<TrialMatch> getTrialMatchByHugoSymbol(String hugoSymbol);

    TrialMatch saveTrialMatch(TrialMatch trialMatch);

    void delete(String id);
}
