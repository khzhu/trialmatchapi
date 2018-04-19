package com.pughlab.trialmatchapi.web;

import com.pughlab.trialmatchapi.domain.Clinical;
import com.pughlab.trialmatchapi.repository.ClinicalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClinicalServiceImpl implements ClinicalService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ClinicalRepository clinicalRepository;

    @Autowired
    public void setClinicalRepository(ClinicalRepository trialMatchRepository) {
        this.clinicalRepository = trialMatchRepository;
    }

    @Override
    public List<Clinical> listAllClinicals() {
        logger.debug("listAllClinicals called");
        return clinicalRepository.findAll();
    }

    @Override
    public Clinical getClinicalById(String id) {
        logger.debug("getClinicalById called");
        return clinicalRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        logger.debug("delete called");
        clinicalRepository.delete(getClinicalById(id));
    }
}
