package com.pughlab.trialmatchapi.web;

import com.pughlab.trialmatchapi.domain.Trial;
import java.util.List;

public interface TrialService {
    List<Trial> listAllTrials();

    Trial getTrialById(String id);

    Trial getTrialByNctId(String nctId);

    void delete(String id);
}

