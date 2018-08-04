package com.pughlab.trialmatchapi.web;

import com.pughlab.trialmatchapi.domain.Clinical;
import java.util.List;

public interface ClinicalService {
    List<Clinical> listAllClinicals();

    Clinical getClinicalById(String id);

    Clinical getClinicalBySampleId(String sampleId);

    void delete(String id);
}

