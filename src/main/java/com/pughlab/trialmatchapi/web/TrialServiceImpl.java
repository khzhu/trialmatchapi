package com.pughlab.trialmatchapi.web;

import com.pughlab.trialmatchapi.domain.Trial;
import com.pughlab.trialmatchapi.repository.TrialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrialServiceImpl implements TrialService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TrialRepository trialRepository;

    @Autowired
    public void setTrialRepository(TrialRepository trialRepository) {
        this.trialRepository = trialRepository;
    }

    @Override
    public List<Trial> listAllTrials() {
        logger.debug("listAllTrials called");
        return trialRepository.findAll();
    }

    @Override
    public Trial getTrialById(String id) {
        logger.debug("getTrialById called");
        return trialRepository.findById(id);
    }

    @Override
    public Trial getTrialByNctId(String nctId) {
        logger.debug("getTrialByNctID called");
        return trialRepository.findTrialByNctID(nctId);
    }

    @Override
    public void delete(String id) {
        logger.debug("delete called");
        trialRepository.delete(getTrialById(id));
    }
}

